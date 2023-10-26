package seedu.address.model.person;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's annual leave in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAnnualLeave(String)}
 */
public class AnnualLeave {
    public static final String MESSAGE_CONSTRAINTS =
            "Number of days of annual leave remaining should only contain numerical digits. "
                + "It should not contain dashes or spaces.";

    public static final String MESSAGE_LEAVE_CONSTRAINTS =
            "Number of days of annual leave taken should not exceed the total limit." +
                    "You can only apply leave for this year and next year.";

    public static final String MESSAGE_EXPIRED_LEAVE = "Date of the leave that you are trying to add is already over.";
    public static final String MESSAGE_DUPLICATE_LEAVE = "Some or all the leave(s) that you " +
            "are trying to add has already been added. Please check again.";
    public static final String MESSAGE_INVALID_LEAVE = "The end date of the leave must be after the start date.";

    /*
     * The first character of the annual leave must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "\\d+";

    public final String value;

    public List<LocalDate> leaveList;

    /**
     * Constructs a {@code AnnualLeave}.
     *
     * @param annualLeave A valid annual leave.
     */
    public AnnualLeave(String annualLeave) {
        requireNonNull(annualLeave);
        checkArgument(isValidAnnualLeave(annualLeave), MESSAGE_CONSTRAINTS);
        value = annualLeave;
        this.leaveList = new ArrayList<>();
    }

    /**
     * Returns true if a given string is a valid number of days.
     */
    public static boolean isValidAnnualLeave(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        String result = value;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (LocalDate date : this.leaveList) {
            String formattedDate = date.format(formatter);
            result += " | " + formattedDate;
        }
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AnnualLeave)) {
            return false;
        }

        AnnualLeave otherAnnualLeave = (AnnualLeave) other;
        return value.equals(otherAnnualLeave.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Adds in a single day of leave.
     * @param startDate of the leave to be added to the leaveList
     */
    public void addLeave(LocalDate startDate) {
        this.leaveList.add(startDate);
    }

    /**
     * Adds in multiple days of leave.
     * @param startDate of the leave to be added to the leaveList
     * @param endDate of the leave to be added to the leaveList
     */
    public void addLeave(LocalDate startDate, LocalDate endDate) {
        long numOfDays = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        for (int i = 0; i < numOfDays; i++) {
            this.leaveList.add(startDate.plusDays(i));
        }
    }

    /**
     * Returns true if the number of days of leave to be added does not exceed the remaining days of leave
     * that the emplyee has, else false.
     * @param startDate of the leave to be added to the leaveList
     * @param endDate of the leave to be added to the leaveList
     * @return true or false depending on whether the total number of days of leave is exceeded
     */
    public boolean isValidAddLeave(LocalDate startDate, LocalDate endDate) {
        LocalDate currentDate = LocalDate.now();
        if (endDate.getYear() > currentDate.getYear() + 1) {
            return false;
        }
        long numOfDays = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        int numOfLeaveForCurrYear = 0;
        int numOfLeaveForNextYear = 0;
        for (int i = 0; i < numOfDays; i++) {
            if (startDate.plusDays(i).getYear() == currentDate.getYear()) {
                numOfLeaveForCurrYear += 1;
            } else if (startDate.plusDays(i).getYear() == currentDate.getYear() + 1) {
                numOfLeaveForNextYear += 1;
            }
        }
        if (numOfLeaveForCurrYear + this.numOfLeaveUsedForCurrYear() <= this.getTotalNumOfLeave() &&
                numOfLeaveForNextYear + this.numOfLeaveUsedForNextYear() <= this.getTotalNumOfLeave()) {
            return true;
        }
        return false;
    }

    /**
     * To get the number of days of leave remaining for the current year.
     * @return the number of days of leave left for the current year
     */
    public int numOfLeaveLeftForCurrYear() {
        return this.getTotalNumOfLeave() - this.numOfLeaveUsedForCurrYear();
    }

    /**
     * To get the number of days of leave remaining for the next year.
     * @return the number of days of leave left for the next year
     */
    public int numOfLeaveLeftForNextYear() {
        return this.getTotalNumOfLeave() - this.numOfLeaveUsedForNextYear();
    }

    /**
     * To get the number of days of leave used for the current year.
     * @return the number of days of leave used for the current year
     */
    public int numOfLeaveUsedForCurrYear() {
        int numOfDays = 0;
        LocalDate currentDate = LocalDate.now();
        for (LocalDate date: this.leaveList) {
            if (date.getYear() == currentDate.getYear()) {
                numOfDays += 1;
            }
        }
        return numOfDays;
    }

    /**
     * To get the number of days of leave used for the next year.
     * @return the number of days of leave used for the next year
     */
    public int numOfLeaveUsedForNextYear() {
        int numOfDays = 0;
        LocalDate currentDate = LocalDate.now();
        for (LocalDate date: this.leaveList) {
            if (date.getYear() == currentDate.getYear() + 1) {
                numOfDays += 1;
            }
        }
        return numOfDays;
    }

    /**
     * To get the total number of days of leave.
     * @return the total number of days of leave
     */
    public int getTotalNumOfLeave() {
        return parseInt(this.value);
    }

    /**
     * Returns true if the leave to be added has already been added before, else false.
     * @param startDate of the leave to be added to the leaveList
     * @param endDate of the leave to be added to the leaveList
     * @return true or false depending on whether there are duplicate leave
     */
    public boolean containsDuplicateLeave(LocalDate startDate, LocalDate endDate) {
        LocalDate tempEndDate;
        if (endDate == null) {
            tempEndDate = startDate;
        } else {
            tempEndDate = endDate;
        }
        for (LocalDate date: this.leaveList) {
            if (!date.isBefore(startDate) && !date.isAfter(tempEndDate)) {
                return true;
            }
        }
        return false;
    }

    /**
     * To get whether the employee is working or on leave on the current day.
     * @return "Working" if the employee is not one leave, else return "On Leave"
     */
    public String getWorkingStatus() {
        LocalDate currentDate = LocalDate.now();
        for (LocalDate date: this.leaveList) {
            if (currentDate.equals(date)) {
                return "On Leave";
            }
        }
        return "Working";
    }

}
