package com.bita.lost.repo

interface ListRepository {}

class ListRepositoryImpl(private val dataSource: ListDataSource) : ListRepository {}