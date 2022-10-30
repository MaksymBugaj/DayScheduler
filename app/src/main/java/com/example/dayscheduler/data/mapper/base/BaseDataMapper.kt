package com.example.dayscheduler.data.mapper.base

interface BaseDataMapper <Data, Domain> {
    fun mapToData(domainModel: Domain): Data
    fun mapFromData(dataModel: Data): Domain
}

fun <Data, Domain> Data.toDomain(mapper: BaseDataMapper<Data, Domain>): Domain =
    mapper.mapFromData(this)

fun <Data, Domain> Domain.toData(mapper: BaseDataMapper<Data, Domain>): Data =
    mapper.mapToData(this)