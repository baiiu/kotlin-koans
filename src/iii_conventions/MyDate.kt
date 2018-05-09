package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override operator fun compareTo(other: MyDate): Int =
            when {
                this.year != other.year -> this.year - other.year
                this.month != other.month -> this.month - other.month
                else -> this.dayOfMonth - other.dayOfMonth
            }

}

operator fun MyDate.rangeTo(other: MyDate): DateRange {
    return DateRange(this, other)
}

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR;
}

class RepeatedTimeInterval(val timeInterval: TimeInterval, val number: Int)

operator fun TimeInterval.times(number: Int) = RepeatedTimeInterval(this, number)

operator fun MyDate.plus(timeInterval: TimeInterval) = addTimeIntervals(timeInterval, 1)
operator fun MyDate.plus(timeIntervals: RepeatedTimeInterval) = addTimeIntervals(timeIntervals.timeInterval, timeIntervals.number)


class DateRange(val start: MyDate, val endInclusive: MyDate) : Iterable<MyDate> {

    operator fun contains(date: MyDate): Boolean {
        return date in start..endInclusive
    }

    override operator fun iterator(): Iterator<MyDate> = DateIterator(this)
}

class DateIterator(val dateRange: DateRange) : Iterator<MyDate> {
    var current: MyDate = dateRange.start;

    override fun next(): MyDate {
        val result = current;
        current = result.nextDay()
        return result
    }

    override fun hasNext(): Boolean = current <= dateRange.endInclusive
}
