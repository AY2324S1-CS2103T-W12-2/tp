//package seedu.address.logic.commands;
//
//import seedu.address.commons.core.index.Index;
//import seedu.address.commons.util.ToStringBuilder;
//import seedu.address.logic.Messages;
//import seedu.address.logic.commands.exceptions.CommandException;
//import seedu.address.model.Model;
//import seedu.address.model.person.Person;
//
//import java.util.List;
//
//import static java.util.Objects.requireNonNull;
//
//public class ReadCommand {
//
//    public static final String COMMAND_WORD = "read";
//
//    public static final String MESSAGE_USAGE = COMMAND_WORD
//                + ": Read the person's (Identified by the index number used in the displayed person list)"
//                + " specific field."
//                + "Parameters: INDEX (must be a positive integer)\n"
//                + "Example: " + COMMAND_WORD + " 1 BC";
//
//    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";
//
//    private final Index targetIndex;
//    private final String targetField;
//
//    public ReadCommand(Index targetIndex, String targetField) {
//        this.targetIndex = targetIndex;
//        this.targetField = targetField;
//    }
//
//    @Override
//    public CommandResult execute(Model model) throws CommandException {
//        requireNonNull(model);
//        List<Person> lastShownList = model.getFilteredPersonList();
//
//        if (targetIndex.getZeroBased() >= lastShownList.size()) {
//            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
//        }
//
//        Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
//        model.deletePerson(personToDelete);
//        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(personToDelete)));
//    }
//
//    @Override
//    public boolean equals(Object other) {
//        if (other == this) {
//            return true;
//        }
//
//        // instanceof handles nulls
//        if (!(other instanceof seedu.address.logic.commands.DeleteCommand)) {
//            return false;
//        }
//
//        seedu.address.logic.commands.DeleteCommand otherDeleteCommand = (seedu.address.logic.commands.DeleteCommand) other;
//        return targetIndex.equals(otherDeleteCommand.targetIndex);
//    }
//
//    @Override
//    public String toString() {
//        return new ToStringBuilder(this)
//                .add("targetIndex", targetIndex)
//                .toString();
//    }
//}
//
//
