package com.spirit.essential.common.utils;

import com.spirit.essential.common.exception.MainStageException;
import com.spirit.tba.Exception.TsException;
import com.spirit.tba.core.TsRpcEventParser;
import com.spirit.tba.core.TsRpcMessageBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TBase;

import static com.spirit.essential.common.exception.ExceptionCode.SERIALIZE_EXCEPTION;

@Slf4j
public class TbaUtil<TEvent extends TBase> {

    public byte [] Serialize(TEvent entify, int buf_size) throws MainStageException {
        try {
            TsRpcMessageBuilder<TEvent> builder = new TsRpcMessageBuilder<TEvent>(entify, buf_size);
            return builder.Serialize().OutStream().GetBytes();
        } catch (TsException e) {
            throw new MainStageException(SERIALIZE_EXCEPTION);
        }
    }

    public TEvent Deserialize(byte[] msg, Class<TEvent> clazz) throws IllegalAccessException, TsException, InstantiationException {
        TsRpcEventParser<TEvent> parser = new TsRpcEventParser<>(msg, msg.length);
        return parser.ToEvent(clazz, 0);
    }
}
