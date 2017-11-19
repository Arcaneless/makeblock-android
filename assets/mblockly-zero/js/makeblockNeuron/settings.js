extend(MBlockly.Settings, {
    // 关闭block块过滤
    FILTER_BLOCKS_FOR_BOARDS: true,

    // close debug mode.
    DEBUG: false,
    OPEN_LOG: false,
    OPEN_HIGHLIGHT: true
});

if(location.host.indexOf('localhost') > -1 || location.host.indexOf('192.168') > -1 || location.host.indexOf('127.0.0.1') > -1
    || location.host.indexOf('0.0.0.0') > -1) {
    MBlockly.Settings.DEBUG = true;
    MBlockly.Settings.OPEN_LOG = true;
}