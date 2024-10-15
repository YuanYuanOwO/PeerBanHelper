export default {
  'page.settings.tab.config.base.title': 'Base Config',
  'page.settings.tab.config.form.checkInterval': 'Check Interval',
  'page.settings.tab.config.unit.ms': 'ms',
  'page.settings.tab.config.form.banDuration': 'Ban Duration',
  'page.settings.tab.config.form.ingoreAddress': 'Ignore Address',
  'page.settings.tab.config.form.ingoreAddress.tooltip':
    'Bypass list, all peers comes from those IPs will bypass all checks',
  'page.settings.tab.config.module.title': 'Module Config',
  'page.settings.tab.config.module.enable': 'Enable',

  'page.settings.tab.config.module.peerIdBlackList.if': 'If',
  'page.settings.tab.config.module.peerIdBlackList.hit': 'Hit',
  'page.settings.tab.config.module.peerIdBlackList.miss': 'Miss',
  'page.settings.tab.config.module.banRuleTips.STARTS_WITH': 'Starts with',
  'page.settings.tab.config.module.banRuleTips.ENDS_WITH': 'Ends with',
  'page.settings.tab.config.module.banRuleTips.LENGTH': 'Match the string length',
  'page.settings.tab.config.module.banRuleTips.CONTAINS': 'Contains',
  'page.settings.tab.config.module.banRuleTips.EQUALS': 'Equals',
  'page.settings.tab.config.module.banRuleTips.REGEX': 'Regex',

  'page.settings.tab.config.module.banRuleTips.DEFAULT': 'Default',
  'page.settings.tab.config.module.banRuleTips.TRUE': 'Ban',
  'page.settings.tab.config.module.banRuleTips.FALSE': 'Exclude',

  'page.settings.tab.config.module.peerIdBlackList': 'PeerId blacklist',
  'page.settings.tab.config.module.peerIdBlackList.individualBanTime': 'Use global ban duration',
  'page.settings.tab.config.module.peerIdBlackList.banPeerId': 'Banned PeerId',
  'page.settings.tab.config.module.peerIdBlackList.rule': 'Rule',

  'page.settings.tab.config.module.clientNameBlackList': 'ClientName blacklist',

  'page.settings.tab.config.module.progressCheatBlocker': 'Cheatting Progress blocker',
  'page.settings.tab.config.module.progressCheatBlocker.tips':
    'Note: Sometimes it may incorrectly ban some clients who enabled "Super Seeding", but in most cases, it can accurately detect the cheatting/bad peers.',
  'page.settings.tab.config.module.progressCheatBlocker.minSize': 'Minimum file size',
  'page.settings.tab.config.module.progressCheatBlocker.minSize.tips':
    'Skip the check if torrent smaller than this value, unit: bytes, peer may have to no chance to sync the progress',
  'page.settings.tab.config.module.progressCheatBlocker.maxDifference':
    'Maximum progress difference',
  'page.settings.tab.config.module.progressCheatBlocker.maxDifference.tips':
    "PeerBanHelper will use BT client recorded data to check the actual uploaded bytes, and calculate minimal progress that this peer should have and compare with peer reported progress. If peer reported progress is smaller than our calculated progress too much, we will consider it's cheating. It works well on detecting new various and cheat clients.",
  'page.settings.tab.config.module.progressCheatBlocker.progressRewindDetection':
    'Progress rewind detection',
  'page.settings.tab.config.module.progressCheatBlocker.rewindMaxDifference':
    'Maximum rewind progress difference',
  'page.settings.tab.config.module.progressCheatBlocker.rewindMaxDifference.tips':
    'Sometimes the pisces may break during transfer, client may drop those pisces, we allow client have rewind in reasonable range',
  'page.settings.tab.config.module.progressCheatBlocker.block_excessive_clients':
    'Block excessive clients',
  'page.settings.tab.config.module.progressCheatBlocker.block_excessive_clients.tips':
    'Block clients that download more than the torrent size',
  'page.settings.tab.config.module.progressCheatBlocker.excessive_threshold':
    'Excessive download threshold',
  'page.settings.tab.config.module.progressCheatBlocker.excessive_threshold.tips':
    'IsExcessive = uploaded > (torrent_size * excessive-threshold)',
  'page.settings.tab.config.module.progressCheatBlocker.ipv4prefixlength': 'IPv4 prefix length',
  'page.settings.tab.config.module.progressCheatBlocker.ipv6prefixlength': 'IPv6 prefix length',
  'page.settings.tab.config.module.progressCheatBlocker.ipprefixLength.tips':
    'IPs from the same subnet are considered as the same user',
  'page.settings.tab.config.module.progressCheatBlocker.banDuration': 'Ban Duration',
  'page.settings.tab.config.module.progressCheatBlocker.enablePersist': 'Enable persist recording',
  'page.settings.tab.config.module.progressCheatBlocker.enablePersist.tips':
    'Enable this feature may increase disk I/O and may affect performance, even may cause cache wear on embedded devices',
  'page.settings.tab.config.module.progressCheatBlocker.persistDuration': 'Persist duration',
  'page.settings.tab.config.module.progressCheatBlocker.maxWaitDuration':
    'Max wait duration before ban',
  'page.settings.tab.config.module.progressCheatBlocker.maxWaitDuration.tips':
    "Sometimes due the network issue, the peer may cannot sync the progress information on time, When a Peer reached ban condition, the timer will start and Peer will be banned after timer timed out if Peer's progress not update to excepted value on time ",
  'page.settings.tab.config.module.progressCheatBlocker.enableFastPCBTest': 'Enable fast PCB test',
  'page.settings.tab.config.module.progressCheatBlocker.enableFastPCBTest.tips':
    'This option will allow PCB ban the Peer from downloader for disconnect it, this will heat up progress reset check quickly.'
}
