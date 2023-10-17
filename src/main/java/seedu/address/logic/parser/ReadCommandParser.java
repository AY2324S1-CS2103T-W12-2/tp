package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.ReadCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ReadCommand object
 */
public class ReadCommandParser implements Parser<ReadCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ReadCommand
     * and returns a ReadCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ReadCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReadCommand.MESSAGE_USAGE));
        }

        String[] fields = trimmedArgs.split("/");

        if (fields.length < 2) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReadCommand.MESSAGE_USAGE));
        }

        Index index = ParserUtil.parseIndex(fields[0].trim());
        String fieldName = fieldNameToString(fields[1]);
        return new ReadCommand(index, fieldName);
    }

    /**
     * Returns a string representation of the specific field name of a person.
     *
     * @param fieldToRead The field name.
     * @return The information specified by the field.
     * @throws CommandException if the field is invalid.
     */
    public String fieldNameToString(String fieldToRead) throws ParseException {
        String fullFieldName;

        switch (fieldToRead) {
        case "p":
            fullFieldName = "phone";
            break;
        case "a":
            fullFieldName = "address";
            break;
        case "e":
            fullFieldName = "email";
            break;
        case "b":
            fullFieldName = "bank account";
            break;
        case "al":
            fullFieldName = "annual leave";
            break;
        case "j":
            fullFieldName = "join date";
            break;
        case "s":
            fullFieldName = "salary";
            break;
        default:
            throw new ParseException(Messages.MESSAGE_INVALID_FIELD_TO_READ);
        }
        return fullFieldName;
    }
}


