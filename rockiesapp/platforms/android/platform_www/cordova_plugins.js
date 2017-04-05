cordova.define('cordova/plugin_list', function(require, exports, module) {
module.exports = [
    {
        "id": "ionic-plugin-keyboard.keyboard",
        "file": "plugins/ionic-plugin-keyboard/www/android/keyboard.js",
        "pluginId": "ionic-plugin-keyboard",
        "clobbers": [
            "cordova.plugins.Keyboard"
        ],
        "runs": true
    },
    {
        "id": "phonegap-plugin-barcodescanner.BarcodeScanner",
        "file": "plugins/phonegap-plugin-barcodescanner/www/barcodescanner.js",
        "pluginId": "phonegap-plugin-barcodescanner",
        "clobbers": [
            "cordova.plugins.barcodeScanner"
        ]
    }
];
module.exports.metadata = 
// TOP OF METADATA
{
    "ionic-plugin-keyboard": "2.2.1",
    "cordova-plugin-compat": "1.1.0",
    "phonegap-plugin-barcodescanner": "6.0.6"
};
// BOTTOM OF METADATA
});