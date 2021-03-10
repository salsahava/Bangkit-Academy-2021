package com.dicoding.salsahava.f1worldchampions

data class WorldChamp(
    var name: String = "",
    var entries: Int = 0,
    var wins: Int = 0,
    var podiums: Int = 0,
    var careerPoints: Any = 0,
    var polePositions: Int = 0,
    var fastestLaps: Int = 0,
    var detail: String = "",
    var photo: Int = 0
)