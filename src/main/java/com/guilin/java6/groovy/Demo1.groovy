import org.apache.commons.lang.StringUtils
import org.joda.time.DateTime
import org.joda.time.Days

import java.util.regex.Pattern

def d = 610340410000
println(new DateTime(d))
println(d.toString().length())

println(new DateTime(1965, 5, 5, 0, 0, 0).getMillis())

def d2 = -147081600000
println(new DateTime(d2))

println(Long.MAX_VALUE)
println(Long.MIN_VALUE)

def a = Pattern.compile('^-?\\d+$')
println(a.matcher('-12').find())
println(a.matcher('0').find())
println(a.matcher('2').find())
println(a.matcher('-0').find())
println(a.matcher('22.4').find())

println('-0'.toLong())

def from = new DateTime("2015-02-02")
def to = new DateTime("2015-02-05")
println(Days.daysBetween(from, to).days)

println('22'.toDouble())

println(StringUtils.replace("a,b,c,", ",", ""));

println("hello 张三".toUpperCase())
println("张三12".toUpperCase())