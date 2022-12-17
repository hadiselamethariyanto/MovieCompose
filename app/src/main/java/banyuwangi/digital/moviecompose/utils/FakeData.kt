package banyuwangi.digital.moviecompose.utils

import banyuwangi.digital.core.domain.model.CollectionDomain

object FakeData {

    val collections = listOf(
        CollectionDomain(
            id = 1,
            name = "Marvel Universe",
            backdropPath = "https://static.wikia.nocookie.net/hulksmash/images/1/12/Marvel-Universe-Logo-500.jpg/revision/latest?cb=20120721212320"
        ),
        CollectionDomain(
            id = 2,
            name = "DC Universe",
            backdropPath = "https://static.wikia.nocookie.net/youngjustice/images/f/f6/DC_Universe_Infinite.png/revision/latest?cb=20220914121020"
        ),
        CollectionDomain(
            id = 3,
            name = "Bumi Langit Universe",
            backdropPath = "https://static.wikia.nocookie.net/jagatsinemabumilangit/images/d/db/Jagat_Sinema_Bumilangit.png/revision/latest?cb=20190928134146&path-prefix=id"
        )
    )


}