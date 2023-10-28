package configs

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ResponseWrapperSchema(
    @Contextual
    val items: List<RepositorySchema>
)

@Serializable
internal data class RepositorySchema(
    val id: Int,
    val name: String,
    val owner: OwnerSchema
)

@Serializable
internal data class OwnerSchema(
    @SerialName("login")
    val name: String
)
