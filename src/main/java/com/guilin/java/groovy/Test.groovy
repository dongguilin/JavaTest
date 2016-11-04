import org.joda.time.*

import java.text.SimpleDateFormat
import java.util.regex.Pattern

def res = 0


def span = 'h'

try {
//    def from = doc[from].value
//    def to = doc[to].value

    def from = null, to = null

    def d1 = '2016-04-23 11:23:05.094'
//    def from='20150203'
    def d2 = '20160425112305.095'
//    def to='20150222'
    def format1 = 'yyyy-MM-dd HH:mm:ss.SSS'
    def format2 = 'yyyyMMddHHmmss.SSS'


    def fd, td

    if (from == null) {
        fd = new DateTime(new SimpleDateFormat(format1).parse(d1).getTime())
    } else {
        boolean flag = Pattern.compile("\\d{10,13}").matcher(from).find()
        if (flag) {
            if (from.length() == 10) {
                fd = new DateTime(from.toLong() * 1000)
            } else if (from.length() == 13) {
                fd = new DateTime(from.toLong())
            }
        } else {
            fd = new DateTime(new SimpleDateFormat(format1).parse(from).getTime())
        }
    }
    if (to == null) {
        td = new DateTime(new SimpleDateFormat(format2).parse(d2).getTime())
    } else {
        boolean flag = Pattern.compile("\\d{10,13}").matcher(to).find()
        if (flag) {
            if (to.length() == 10) {
                td = new DateTime(to.toLong() * 1000)
            } else if (to.length() == 13) {
                td = new DateTime(to.toLong())
            }
        } else {
            td = new DateTime(new SimpleDateFormat(format2).parse(to).getTime())
        }
    }

    if (fd != null && td != null) {

        println(fd)
        println(td)

        if (span != null) {
            switch (span) {
                case 'y': res = Years.yearsBetween(fd, td).years; break;
                case 'M': res = Months.monthsBetween(fd, td).months; break;
                case 'w': res = Weeks.weeksBetween(fd, td).weeks; break;
                case 'd': res = Days.daysBetween(fd, td).days; break;
                case 'h': res = Hours.hoursBetween(fd, td).hours; break;
                case 'm': res = Minutes.minutesBetween(fd, td).minutes; break;
                case 's': res = Seconds.secondsBetween(fd, td).seconds; break;
                default: res = 0; break;
            }

        } else {
            res = Days.daysBetween(fd, td).getDays()
        }
    }


} catch (Throwable e) {
//    throw e
}

println(res)


