package com.pok.onli.pokies.com.Utils98po2

import android.util.Base64

const val APPSFLYER_CAMPAIGN_TAG_98po2 = "campaign"
const val APPSFLYER_STATUS_TAG_98po2 = "af_status"
const val APPSFLYER_ADGROUP_TAG_98po2 = "adgroup"
const val APPSFLYER_ADSET_TAG_98po2 = "adset"
const val APPSFLYER_AF_CHANNEL_TAG_98po2 = "af_channel"
const val APPSFLYER_MEDIA_SOURCE_TAG_98po2 = "media_source"

const val FB_DEFAULT_KEY_98po2 = "pocdefkey"
const val FB_BLACK_KEY_98po2 = "pokblackpage"
const val FB_WHITE_KEY_98po2 = "pokwhitepage"
const val CODED_ONESIGNAL_98po2 = "YmE0MjM2NjYtMDRjMi00NjNhLWI1YzAtZGQwMzZkNDJhMmM1"
const val CODE_APPSFLYER_98po2 = "NkIzRzhmdW84dERmVmMyZzNCSHhyQQ=="

fun decodeString98po2 (str98po2: String) = String(Base64.decode(str98po2, Base64.DEFAULT))