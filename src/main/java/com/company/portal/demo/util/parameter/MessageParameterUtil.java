package com.company.portal.demo.util.parameter;

import com.company.portal.demo.enums.MessageParameterEnum;
import com.company.portal.demo.payload.dto.UserDto;
import com.company.portal.demo.service.MessageParameterService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.TriConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
@DependsOn("liquibase")
@RequiredArgsConstructor
public class MessageParameterUtil {

    private static final String REGEX_PATTERN = "\\{[^{}]*\\}";

    private final MessageParameterService messageParameterService;

    Map<String, TriConsumer<String, StringBuilder, UserDto>> messageReplacementUserStrategyMap = new HashMap<>();

    private Map<MessageParameterEnum, String> messageParameters;

    private Logger logger = LoggerFactory.getLogger(MessageParameterUtil.class);

    @PostConstruct
    private void init() {
        messageParameters = messageParameterService.getAllMessageParameter();
        setMessageReplacementUserStrategyMap();
    }

    private void setMessageReplacementUserStrategyMap() {
        messageReplacementUserStrategyMap.put(messageParameters.get(MessageParameterEnum.USERNAME), replaceUserName());
        messageReplacementUserStrategyMap.put(messageParameters.get(MessageParameterEnum.VERIFICATION_CODE), replaceVerificationCode());
    }

    public String generateTextContent(UserDto userDto, String text) {
        StringBuilder messageBuilder = new StringBuilder().append(text);
        regexAndReplaceFieldsInUser(userDto, messageBuilder);
        return messageBuilder.toString();
    }

    private void regexAndReplaceFieldsInUser(UserDto userDto, StringBuilder messageBuilder) {
        Pattern.compile(REGEX_PATTERN).matcher(messageBuilder).results().map(MatchResult::group).collect(Collectors.toList()).forEach(parameter -> getConsumerByUserParameterType(parameter).accept(parameter, messageBuilder, userDto));
    }

    private TriConsumer<String, StringBuilder, UserDto> getConsumerByUserParameterType(String parameter) {
        var function = messageReplacementUserStrategyMap.get(parameter);
        if (Objects.nonNull(function)) {
            return function;
        }
        changedParameterWarnLog(parameter);
        return (String param, StringBuilder message, UserDto userDto) -> {
        };
    }

    private void changedParameterWarnLog(String parameter) {
        logger.warn("Incorrect parameter in message: {}", parameter);
    }

    private TriConsumer<String, StringBuilder, UserDto> replaceUserName() {
        return (parameter, message, userDto) ->
                message.replace(message.indexOf(parameter),
                message.indexOf(parameter) + parameter.length(),
                        userDto.getUserName());
    }
    private TriConsumer<String, StringBuilder, UserDto> replaceVerificationCode() {
        return (parameter, message, userDto) ->
                message.replace(message.indexOf(parameter),
                        message.indexOf(parameter) + parameter.length(),
                        userDto.getResetToken());
    }

}
