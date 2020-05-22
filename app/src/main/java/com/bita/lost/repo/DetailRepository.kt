package com.bita.lost.repo

interface DetailRepository {

}

class DetailRepositoryImpl(private val detailDataSource: DetailDataSource) : DetailRepository {


}