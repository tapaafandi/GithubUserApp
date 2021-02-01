package com.tapaafandi.githubuserapp.data.network.model

import com.tapaafandi.githubuserapp.domain.model.UserSearch
import com.tapaafandi.githubuserapp.domain.util.DomainMapper

class UserSearchDtoMapper : DomainMapper<UserSearchDto, UserSearch> {

    override fun mapToDomainModel(model: UserSearchDto): UserSearch {
        return UserSearch(
            username = model.login,
            avatarUrl = model.avatarUrl
        )
    }

    override fun mapFromDomainModel(domainModel: UserSearch): UserSearchDto {
        return UserSearchDto(
            login = domainModel.username,
            avatarUrl = domainModel.avatarUrl
        )
    }

    fun toDomainList(initial: List<UserSearchDto>): List<UserSearch> {
        return initial.map { mapToDomainModel(it) }
    }

    fun fromDomainList(initial: List<UserSearch>): List<UserSearchDto> {
        return initial.map { mapFromDomainModel(it) }
    }
}