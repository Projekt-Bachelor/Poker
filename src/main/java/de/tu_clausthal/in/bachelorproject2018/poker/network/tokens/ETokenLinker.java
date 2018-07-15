package de.tu_clausthal.in.bachelorproject2018.poker.network.tokens;

import javax.annotation.Nonnull;
import java.text.MessageFormat;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public enum ETokenLinker implements Function<UUID, String> {

    INSTANCE;

    private Map<UUID, String> m_sessionLinkMap = new ConcurrentHashMap<>();

    @Nonnull
    @Override
    public String apply(UUID p_uuid) {
        final String l_sessionId = m_sessionLinkMap.get(p_uuid);
        if (l_sessionId.isEmpty())
            throw new RuntimeException(MessageFormat.format("UUID [{0}] existiert nicht", p_uuid));

        return l_sessionId;
    }

    public UUID add(@Nonnull UUID p_uuid, @Nonnull String p_sessionId){

        if (m_sessionLinkMap.containsKey(p_uuid))
            throw new RuntimeException(MessageFormat.format("UUID [{0}] existiert schon", p_uuid));

        m_sessionLinkMap.put(p_uuid, p_sessionId);

        return p_uuid;
    }

    public void remove(@Nonnull UUID p_uuid){
        m_sessionLinkMap.remove(p_uuid);
    }

}
