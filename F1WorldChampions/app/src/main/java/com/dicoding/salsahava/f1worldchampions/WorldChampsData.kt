package com.dicoding.salsahava.f1worldchampions

object WorldChampsData {
    private val worldChampNames = arrayOf(
        "Lewis Hamilton",
        "Nico Rosberg",
        "Sebastian Vettel",
        "Jenson Button",
        "Kimi Räikkönen",
        "Fernando Alonso",
        "Michael Schumacher",
        "Mika Häkkinen",
        "Jacques Villeneuve",
        "Damon Hill",
        "Alain Prost",
        "Nigel Mansell",
        "Ayrton Senna",
        "Nelson Piquet",
        "Niki Lauda"
    )

    private val worldChampEntries = intArrayOf(266, 206, 258, 309, 332, 314, 308, 165, 165, 122, 202, 191, 162, 207, 177)

    private val worldChampWins = intArrayOf(95, 23, 53, 15, 21, 32, 91, 20, 11, 22, 51, 31, 41, 23, 25)

    private val worldChampPodiums = intArrayOf(165, 57, 121, 50, 103, 97, 155, 51, 23, 42, 106, 59, 80, 60, 54)

    private val worldChampCareerPoints = arrayOf(3778, 1594.5, 3018, 1235, 1863, 1899, 1566, 420, 235, 360, 798.5, 482, 614, 485.5, 420.5)

    private val worldChampPolePositions = intArrayOf(98, 30, 57, 8, 18, 22, 68, 26, 13, 20, 33, 32, 65, 24, 24)

    private val worldChampFastestLaps = intArrayOf(53, 20, 38, 8, 46, 23, 77, 25, 9, 19, 41, 30, 19, 23, 24)

    private val worldChampDetails = arrayOf(
        "Sir Lewis Carl Davidson Hamilton MBE HonFREng (born 7 January 1985) is a British racing driver, activist, fashion designer and musician. He currently competes in Formula One for Mercedes, having previously driven for McLaren from 2007 to 2012. In Formula One, Hamilton has won a joint-record seven World Drivers' Championship titles.",
        "Nico Erik Rosberg (born 27 June 1985) is a German former racing driver who won the 2016 Formula One World Championship driving for Mercedes-AMG Petronas Motorsport. The only child of Keke Rosberg (the 1982 Formula One World Champion) and his German wife Sina Rosberg, he was raised primarily in the Principality of Monaco.",
        "Sebastian Vettel (born 3 July 1987) is a German racing driver who competes in Formula One for Aston Martin, having previously driven for BMW Sauber, Toro Rosso, Red Bull and Ferrari. Vettel has won four World Drivers' Championship titles which he won consecutively from 2010 to 2013. He currently holds the record for Formula One's youngest World Champion.",
        "Jenson Alexander Lyons Button MBE (born 19 January 1980) is a British racing driver. He won the 2009 Formula One World Championship, when he drove for the Brawn GP team. After his F1 career, he became champion of the 2018 season of the Super GT Series alongside Naoki Yamamoto, who he shared a Honda racing car with at Team Kunimitsu.",
        "Kimi-Matias Räikkönen (born 17 October 1979), nicknamed \"The Iceman\", is a Finnish racing driver currently driving in Formula One for Alfa Romeo Racing, racing under the Finnish flag. Räikkönen won the 2007 Formula One World Championship driving for Scuderia Ferrari. In addition to this title, he also finished second overall in 2003 and 2005, and third in 2008, 2012 and 2018.",
        "Fernando Alonso Díaz (born 29 July 1981) is a Spanish racing driver currently racing for Alpine in Formula One. He won the series' World Drivers' Championship in 2005 and 2006 with Renault, having also driven for McLaren, Ferrari and Minardi. With Toyota, Alonso won the 24 Hours of Le Mans twice, in 2018 and 2019, and the FIA World Endurance Championship in 2018–19.",
        "Michael Schumacher (born 3 January 1969) is a retired German racing driver who competed in Formula One for Jordan, Benetton, Ferrari and Mercedes. Schumacher has a joint-record seven World Drivers' Championship titles and, at the time of his retirement from the sport in 2012, he held the records for the most wins (91), pole positions (68) and podium finishes (155)—which have since been broken by Lewis Hamilton—while he maintains the records for the most fastest laps (77) and the most races won in a single season (13), amongst others.",
        "Mika Pauli Häkkinen (born 28 September 1968), nicknamed \"The Flying Finn\",[1] is a Finnish former racing driver. He was the 1998 and 1999 Formula One World Champion, driving for McLaren. Häkkinen is one of three Formula One drivers from Finland to win the World Championship, and is the only Finnish driver in history to win it on multiple occasions.",
        "Jacques Joseph Charles Villeneuve OQ (born April 9, 1971) is a Canadian professional auto racing driver. Villeneuve currently competes in the NASCAR Whelen Euro Series, driving the #5 car for FEED Vict Racing in the EuroNASCAR PRO class. He is the son of Formula One driver Gilles Villeneuve, and is the namesake of his uncle, who was also a racer. Villeneuve won the 1995 CART Championship, the 1995 Indianapolis 500 and the 1997 Formula One World Championship, making him only the third driver after Mario Andretti and Emerson Fittipaldi to achieve such a feat.",
        "Damon Graham Devereux Hill, OBE (born 17 September 1960) is a British former racing driver from England. He is the son of Graham Hill, and, along with Nico Rosberg, one of only two sons of a Formula One world champion to also win the title. He started racing on motorbikes in 1981, and after minor success moved on to single-seater racing cars.",
        "Alain Marie Pascal Prost (born 24 February 1955) is a French retired racing driver and a four-time Formula One Drivers' Champion. From 1987 until 2001 he held the record for most Grand Prix victories until Michael Schumacher surpassed Prost's total of 51 victories at the 2001 Belgian Grand Prix. In 1999, Prost received the World Sports Award of the Century in the motor sport category.",
        "Nigel Ernest James Mansell, CBE (born 8 August 1953) is a British former racing driver who won both the Formula One World Championship (1992) and the CART Indy Car World Series (1993). Mansell was the reigning F1 champion when he moved over to CART, becoming the first person to win the CART title in his debut season, and making him the only person to hold both the World Drivers' Championship and the American open-wheel National Championship simultaneously.",
        "Ayrton Senna da Silva (21 March 1960 – 1 May 1994) was a Brazilian racing driver who won the Formula One World Drivers' Championship in 1988, 1990 and 1991. Senna is one of three Formula One drivers from Brazil to win the World Championship and won 41 Grands Prix and 65 pole positions, with the latter being the record until 2006. He died in an accident leading the 1994 San Marino Grand Prix driving for the Williams team.",
        "Nelson Piquet Souto Maior (born 17 August 1952), known as Nelson Piquet, is a Brazilian former racing driver and businessman. Since his retirement, Piquet, a three-time World Champion, has been ranked among the greatest Formula One drivers in various motorsport polls. After retiring from Formula One, Piquet tried his hand at the Indianapolis 500 for two years.",
        "Andreas Nikolaus Lauda (22 February 1949 – 20 May 2019) was an Austrian Formula One driver and aviation entrepreneur. He was a three-time F1 World Drivers' Champion, winning in 1975, 1977 and 1984, and is the only driver in F1 history to have been champion for both Ferrari and McLaren, the sport's two most successful constructors."
    )

    private val worldChampImages = intArrayOf(
        R.drawable.lewis_hamilton,
        R.drawable.nico_rosberg,
        R.drawable.sebastian_vettel,
        R.drawable.jenson_button,
        R.drawable.kimi_raikkonen,
        R.drawable.fernando_alonso,
        R.drawable.michael_schumacher,
        R.drawable.mika_hakkinen,
        R.drawable.jacques_villeneuve,
        R.drawable.damon_hill,
        R.drawable.alain_prost,
        R.drawable.nigel_mansell,
        R.drawable.ayrton_senna,
        R.drawable.nelson_piquet,
        R.drawable.niki_lauda
    )

    val listData: ArrayList<WorldChamp>
        get() {
            val list = arrayListOf<WorldChamp>()
            for (position in worldChampNames.indices) {
                val worldChamp = WorldChamp()
                worldChamp.name = worldChampNames[position]
                worldChamp.entries = worldChampEntries[position]
                worldChamp.wins = worldChampWins[position]
                worldChamp.podiums = worldChampPodiums[position]
                worldChamp.careerPoints = worldChampCareerPoints[position]
                worldChamp.polePositions = worldChampPolePositions[position]
                worldChamp.fastestLaps = worldChampFastestLaps[position]
                worldChamp.detail = worldChampDetails[position]
                worldChamp.photo = worldChampImages[position]

                list.add(worldChamp)
            }
            return list
        }
}