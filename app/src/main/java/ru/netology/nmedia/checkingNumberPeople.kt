package ru.netology.nmedia

fun checkingNumberPeople(sum: Int) : String {
    var str: String = sum.toString()
    var division: Double = sum / 1000.0

    if(sum < 1_000_000){
        if(sum in 1000..9999){
            if ((sum / 100) % 10 == 0 ) {
                str = "%.0f".format(division) + "K"
                return str
            }else{
                if((sum % 100) / 10 > 4){
                    division = (sum - 50) / 1000.0
                    str = "%.1f".format(division) + "K"
                    return str
                }
            }
        }else{
            if (sum > 9999){
                str = "%.0f".format(division) + "K"
                return str
            }
        }
    }else{
        division = sum / 1_000_000.0
        if ((sum / 100_000) % 10 == 0){
            str = "%.0f".format(division) + "M"
            return str
        }else{
            if ((sum / 10_000) % 10 > 4){
                division = (sum - 50_000) / 1_000_000.0
                str = "%.1f".format(division) + "K"
                return str
            }
        }
        str = "%.1f".format(division) + "M"
    }
    return str
}