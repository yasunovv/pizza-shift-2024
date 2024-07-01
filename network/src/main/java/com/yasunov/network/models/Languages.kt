package com.yasunov.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Languages {
    @SerialName("ar")
    AR,

    @SerialName("en")
    EN,

    @SerialName("fr")
    FR,

    @SerialName("it")
    IT,

    @SerialName("no")
    NO,

    @SerialName("ru")
    RU,

    @SerialName("ud")
    UD,

    @SerialName("de")
    DE,

    @SerialName("es")
    ES,

    @SerialName("he")
    HE,

    @SerialName("nl")
    NL,

    @SerialName("pt")
    PT,

    @SerialName("sv")
    SV,

    @SerialName("zh")
    ZH
}
