package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW_PAYSLIP;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.PaySlipGenerator;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;

/**
 * Generates a payslip for an employee.
 */
public class PayslipCommand extends Command {

    public static final String COMMAND_WORD = "payslip";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Generates a payslip in PDF format for the employee "
        + "identified by the index number or name used in the last person listing.\n"
        + "Parameters: INDEX (must be a positive integer) or NAME\n"
        + "Example 1 (index): " + COMMAND_WORD + " 1\n"
        + "Example 2 (name): " + COMMAND_WORD + " /n john\n";

    public static final String MESSAGE_PAYSLIP_SUCCESS = "Payslip generated for Employee: %1$s";

    private final Index index;
    private final NameContainsKeywordsPredicate name;

    /**
     * Creates a PayslipCommand to generate a payslip for the specified employee.
     * @param index of the person in the filtered person list to generate a payslip for.
     */
    public PayslipCommand(Index index) {
        requireNonNull(index);
        this.index = index;
        this.name = null;
    }

    /**
     * Creates a PayslipCommand to generate a payslip for the specified employee.
     * @param name of the person in the filtered person list to generate a payslip for.
     */
    public PayslipCommand(NameContainsKeywordsPredicate name) {
        requireNonNull(name);
        this.name = name;
        this.index = null;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (index != null) {
            return executeByIndex(model);
        } else {
            return executeByName(model);
        }
    }

    /**
     * Executes the command by index.
     * @param model {@code Model} which the command should operate on.
     * @return {@code CommandResult} representing the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     */
    private CommandResult executeByIndex(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToGenerate = lastShownList.get(index.getZeroBased());
        try {
            PaySlipGenerator.generateReport(personToGenerate);
        } catch (Exception e) {
            throw new CommandException(e.getMessage());
        }
        model.updateFilteredPersonList(person -> person.equals(personToGenerate));
        return new CommandResult(String.format(MESSAGE_PAYSLIP_SUCCESS, personToGenerate.getName().toString()));
    }

    /**
     * Executes the command by name.
     * @param model {@code Model} which the command should operate on.
     * @return {@code CommandResult} representing the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     */
    private CommandResult executeByName(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();
        List<Integer> indexes = model.getIndexOfFilteredPersonList(this.name);

        if (indexes.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_NAME);
        }

        if (indexes.size() == 1) {
            Person personToGenerate = lastShownList.get(indexes.get(0) - 1);
            try {
                PaySlipGenerator.generateReport(personToGenerate);
            } catch (Exception e) {
                throw new CommandException(e.getMessage());
            }
            model.updateFilteredPersonList(this.name);
            return new CommandResult(String.format(MESSAGE_PAYSLIP_SUCCESS, personToGenerate.getName().toString()));
        }

        model.updateFilteredPersonList(this.name);
        return new CommandResult(String.format(MESSAGE_PERSONS_LISTED_OVERVIEW_PAYSLIP, lastShownList.size()), indexes);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof PayslipCommand)) {
            return false;
        }

        PayslipCommand otherPayslipCommand = (PayslipCommand) other;
        return index.equals(otherPayslipCommand.index);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("index", index)
            .toString();
    }
}
