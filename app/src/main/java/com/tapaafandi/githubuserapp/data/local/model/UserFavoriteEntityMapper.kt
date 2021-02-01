package com.tapaafandi.githubuserapp.data.local.model

import com.tapaafandi.githubuserapp.domain.model.UserDetail
import com.tapaafandi.githubuserapp.domain.util.DomainMapper

class UserFavoriteEntityMapper : DomainMapper<UserFavoriteEntity, UserDetail> {

    override fun mapToDomainModel(model: UserFavoriteEntity): UserDetail {
        return UserDetail(
            username = model.username,
            name = model.name,
            avatarUrl = model.avatarUrl,
            bio = model.bio,
            location = model.location,
            company = model.company,
            following = model.following,
            followers = model.followers,
            repository = model.repository
        )
    }

    override fun mapFromDomainModel(domainModel: UserDetail): UserFavoriteEntity {
        return UserFavoriteEntity(
            username = domainModel.username!!,
            name = domainModel.name,
            avatarUrl = domainModel.avatarUrl,
            bio = domainModel.bio,
            location = domainModel.location,
            company = domainModel.company,
            following = domainModel.following,
            followers = domainModel.followers,
            repository = domainModel.repository
        )
    }

    fun toDomainList(initial: List<UserFavoriteEntity>): List<UserDetail> {
        return initial.map { mapToDomainModel(it) }
    }

    fun fromDomainList(initial: List<UserDetail>): List<UserFavoriteEntity> {
        return initial.map { mapFromDomainModel(it) }
    }
}