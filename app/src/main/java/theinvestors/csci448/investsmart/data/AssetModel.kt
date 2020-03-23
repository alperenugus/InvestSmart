package theinvestors.csci448.investsmart.data

data class AssetModel(
    var id: Int,
    var email: String,
    var company: String,
    var owned_shares: Int
)