package com.ghostchu.peerbanhelper.scriptengine;

import com.ghostchu.peerbanhelper.module.AbstractRuleFeatureModule;
import com.ghostchu.peerbanhelper.module.CheckResult;
import com.ghostchu.peerbanhelper.module.PeerAction;
import com.ghostchu.peerbanhelper.text.Lang;
import com.ghostchu.peerbanhelper.text.TranslationComponent;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import static com.ghostchu.peerbanhelper.text.TextManager.tlUI;

@Component
@Slf4j
public class ScriptEngine {
    public static final CheckResult OK_CHECK_RESULT = new CheckResult(AbstractRuleFeatureModule.class, PeerAction.NO_ACTION, 0, new TranslationComponent("N/A"), new TranslationComponent("Check passed"));
//
//    public CheckResult runExpression(CompiledScript script, @NotNull Torrent torrent, @NotNull Peer peer, @NotNull Downloader downloader, @NotNull ExecutorService ruleExecuteExecutor) {
//        CheckResult result;
//        try {
//            Map<String, Object> env = script.expression().newEnv();
//            env.put("torrent", torrent);
//            env.put("peer", peer);
//            env.put("downloader", downloader);
//            env.put("cacheable", new AtomicBoolean(false));
//            env.put("server", peerBanHelperServer);
//            env.put("moduleInstance", this);
//            Object returns;
//            if (script.threadSafe()) {
//                returns = script.expression().execute(env);
//            } else {
//                synchronized (script.expression()) {
//                    returns = script.expression().execute(env);
//                }
//            }
//            result = handleResult(script.expression(), returns);
//        } catch (TimeoutException timeoutException) {
//            log.error(tlUI(Lang.MODULE_EXPRESSION_RULE_TIMEOUT, maxScriptExecuteTime), timeoutException);
//            return pass();
//        } catch (Exception ex) {
//            log.error(tlUI(Lang.MODULE_EXPRESSION_RULE_ERROR, expressionMetadata.name()), ex);
//            return pass();
//        }
//        if (result != null && result.action() != PeerAction.NO_ACTION) {
//            return result;
//        } else {
//            return pass();
//        }
//    }


    @Nullable
    public CheckResult handleResult(CompiledScript script, long banDuration, Object returns) {
        if (returns instanceof Boolean status) {
            if (status) {
                return new CheckResult(getClass(), PeerAction.BAN, banDuration,
                        new TranslationComponent(Lang.USER_SCRIPT_RULE),
                        new TranslationComponent(Lang.USER_SCRIPT_RUN_RESULT, script.name(), "true"));
            }
            return null;
        }
        if (returns instanceof Number number) {
            int i = number.intValue();
            if (i == 0) {
                return null;
            } else if (i == 1) {
                return new CheckResult(getClass(), PeerAction.BAN, banDuration,
                        new TranslationComponent(Lang.USER_SCRIPT_RULE),
                        new TranslationComponent(Lang.USER_SCRIPT_RUN_RESULT, script.name(), String.valueOf(number)));
            } else if (i == 2) {
                return new CheckResult(getClass(), PeerAction.SKIP, banDuration,
                        new TranslationComponent(Lang.USER_SCRIPT_RULE),
                        new TranslationComponent(Lang.USER_SCRIPT_RUN_RESULT, script.name(), String.valueOf(number)));
            } else {
                log.error(tlUI(Lang.RULE_ENGINE_INVALID_RETURNS, script));
                return null;
            }
        }
        if (returns instanceof PeerAction action) {
            return new CheckResult(getClass(), action, banDuration,
                    new TranslationComponent(Lang.USER_SCRIPT_RULE),
                    new TranslationComponent(Lang.USER_SCRIPT_RUN_RESULT, script.name(), action.name()));
        }
        if (returns instanceof String string) {
            if (string.isBlank()) {
                return OK_CHECK_RESULT;
            } else if (string.startsWith("@")) {
                return new CheckResult(getClass(), PeerAction.SKIP, banDuration,
                        new TranslationComponent(Lang.USER_SCRIPT_RULE),
                        new TranslationComponent(string.substring(1)));
            } else {
                return new CheckResult(getClass(), PeerAction.BAN, banDuration,
                        new TranslationComponent(Lang.USER_SCRIPT_RULE),
                        new TranslationComponent(Lang.USER_SCRIPT_RUN_RESULT, script.name(), string));
            }
        }
        if (returns instanceof CheckResult checkResult) {
            return checkResult;
        }
        log.error(tlUI(Lang.RULE_ENGINE_INVALID_RETURNS, script.name()));
        return null;
    }

    @Nullable
    public CompiledScript compileScript(File file, String fallbackName, String scriptContent) {
        try (BufferedReader reader = new BufferedReader(new StringReader(scriptContent))) {
            String name = fallbackName;
            String author = "Unknown";
            String version = "null";
            boolean cacheable = true;
            boolean threadSafe = true;
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                if (line.startsWith("#")) {
                    line = line.substring(1).trim();
                    if (line.startsWith("@NAME")) {
                        name = line.substring(5).trim();
                    } else if (line.startsWith("@AUTHOR")) {
                        author = line.substring(7).trim();
                    } else if (line.startsWith("@CACHEABLE")) {
                        cacheable = Boolean.parseBoolean(line.substring(10).trim());
                    } else if (line.startsWith("@VERSION")) {
                        version = line.substring(8).trim();
                    } else if (line.startsWith("@THREADSAFE")) {
                        threadSafe = Boolean.parseBoolean(line.substring(11).trim());
                    }
                }
            }
            AviatorEvaluator.getInstance().validate(scriptContent);
            Expression expression = AviatorEvaluator.getInstance().compile(scriptContent, false);
            return new CompiledScript(
                    file,
                    name,
                    author,
                    cacheable,
                    threadSafe,
                    version,
                    scriptContent,
                    expression
            );
        } catch (Exception e) {
            log.warn("Script Engine unable to compile the script: {}", fallbackName);
            return null;
        }
    }
}
