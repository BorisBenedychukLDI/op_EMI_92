package com.pok.onli.pokies.com.Utils98po2

import android.util.Base64

const val CODED_ONESIGNAL_98po2 = "YmE0MjM2NjYtMDRjMi00NjNhLWI1YzAtZGQwMzZkNDJhMmM1"
const val CODE_BINOM_98po2 = "aHR0cHM6Ly9kcm9wbWVmaWxlcy5jb20v"

fun decodeString98po2 (str98po2: String) = String(Base64.decode(str98po2, Base64.DEFAULT))