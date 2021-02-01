package com.tapaafandi.githubuserapp.data.network.model

import com.tapaafandi.githubuserapp.domain.model.UserDetail
import com.tapaafandi.githubuserapp.domain.util.DomainMapper

class UserDetailDtoMapper : DomainMapper<UserDetailDto, UserDetail> {

    override fun mapToDomainModel(model: UserDetailDto): UserDetail {
        return UserDetail(
            username = model.login,
            name = model.name,
            avatarUrl = model.avatarUrl,
            bio = model.bio,
            location = model.location,
            company = model.company,
            following = model.following,
            followers = model.followers,
            repository = model.publicRepos,
        )
    }

    override fun mapFromDomainModel(domainModel: UserDetail): UserDetailDto {
        return UserDetailDto(
            login = domainModel.username,
            name = domainModel.name,
            avatarUrl = domainModel.avatarUrl,
            bio = domainModel.bio,
            location = domainModel.location,
            company = domainModel.company,
            following = domainModel.following,
            followers = domainModel.followers,
            publicRepos = domainModel.repository,
        )
    }

    fun toDomainList(initial: List<UserDetailDto>): List<UserDetail> {
        return initial.map { mapToDomainModel(it) }
    }

    fun fromDomainList(initial: List<UserDetail>): List<UserDetailDto> {
        return initial.map { mapFromDomainModel(it) }
    }
}