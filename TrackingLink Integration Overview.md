# TrackingLink Integration Overview

- **DLink S2S Click Event API**
```
https://click.deeplink.dev/click/<appid>?pid=__PID__&dl_siteid=__CSITE__&c=__CAMPAIGN_NAME__&dl_channel=__PLACEMENT__&dl_c_id=__CAMPAIGN_ID__&dl_adset=__AID_NAME__&dl_adset_id=__AID__&dl_ad=__CID_NAME__&dl_ad_id=__CID__&dl_ad_type=__CTYPE__&dl_click_lookback=7d&clickid=__Clickid__&advertising_id=__GAID__&idfa=__IDFA__&os=__OS__&dl_ip=__IP__&dl_ua=__UA__&dl_lang=__SL__&utm_code=__utm_code__&redirect=false
```

- **DLink S2S Impression Event API**
```
https://impression.deeplink.dev/impressions/<appid>?pid=__PID__&dl_siteid=__CSITE__&c=__CAMPAIGN_NAME__&dl_channel=__PLACEMENT__&dl_c_id=__CAMPAIGN_ID__&dl_adset=__AID_NAME__&dl_adset_id=__AID__&dl_ad=__CID_NAME__&dl_ad_id=__CID__&dl_ad_type=__CTYPE__&dl_viewthrough_lookback=24h&clickid=__Clickid__&advertising_id=__GAID__&idfa=__IDFA__&os=__OS__&dl_ip=__IP__&dl_ua=__UA__&dl_lang=__SL__&utm_code=__utm_code__&redirect=false
```

The event selected in the DLink console and the corresponding metric names in the media advertising platform are as follows:

|    Name    |    Type                     |    Comment                           |
| -----------------------------------|----------------|-------------------------------|
|    appid    |    string(128)    |    Unique identifier for the application.    |
|    pid    |    string(256)    |    DLink channel unique identifier.    |
|    dl_siteid    |    string(1024)    |    Unique ID to identify the ad publisher (media displaying the ad).    |
|    c    |    string(1024)    |    Provided by the advertiser/publisher. Requires encodeURI.    |
|    dl_channel    |    string(1024)    |    Traffic source channel for ad distribution.    |
|    dl_c_id    |    string(1024)    |    Provided by the advertiser/publisher.    |
|    dl_adset    |    string(1024)    |    Ad group layer (between campaigns and individual ads). Requires encodeURI. Provided by the advertiser/publisher.    |
|    dl_adset_id    |    string(1024)    |    Provided by the advertiser/publisher.    |
|    dl_ad    |    string(1024)    |    Ad name.    |
|    dl_ad_id    |    string(1024)    |    Provided by the advertiser/publisher.    |
|    dl_ad_type    |    string(24)    |    Ad type（text、baner、interstitial、video、rewarded_video、playable、sponsored_content、audio）    |
|    dl_click_lookback    |    string(256)    |    Attribution window (max CTIT: Click-to-Install Time). Users activating the app within this period after clicking the ad/link will be attributed to the channel.    |
|    clickid    |    string(1024)    |    Unique click identifier from the ad platform.    |
|    advertising_id    |    string(64)    |    Google Advertising ID. Use uppercase for Android (requires ad platform support).    |
|    idfa    |    string(64)    |    Uppercase. Used for iOS (requires ad platform support).    |
|    os    |    string(32)    |    Operating system: android or ios.    |
|    dl_ip    |    string(256)    |    Device IP address.    |
|    dl_ua    |    string(1024)    |    User Agent. Requires encodeURI.    |
|    dl_lang    |    string(256)    |    Language.    |
|    link    |    string(2048)    |    Redirect URL types: http_referrer, shortlink, deeplink.    |
|    dl_uid    |    string(64)    |    Customer identifier.    |
|    utm_code    |    string(64)    |    obtain the corresponding utmCode for DLink Partner Marketplace.    |
|    redirect    |    boolen    |    Redirection flag: true, false (default: false).    |
