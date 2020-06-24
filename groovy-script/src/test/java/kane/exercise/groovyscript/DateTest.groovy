package kane.exercise.groovyscript


def date1 = java.time.LocalDate.parse("20200422", "yyyyMMdd")
def date2 = java.time.LocalDate.parse("20200425", "yyyyMMdd")


println(date1.toEpochDay())
println(date2.toEpochDay())

// date2 - date1
def diffInDays = date1.until(date2, java.time.temporal.ChronoUnit.DAYS)
println(diffInDays)


println("0" == "0")

println(java.time.LocalDate.now().format("yyyyMMdd"))



