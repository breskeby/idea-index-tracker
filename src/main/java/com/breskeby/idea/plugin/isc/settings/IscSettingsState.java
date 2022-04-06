package com.breskeby.idea.plugin.isc.settings;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

/**
 * Supports storing the application settings in a persistent way.
 * The {@link State} and {@link Storage} annotations define the name of the data and the file name where
 * these persistent application settings are stored.
 */
@State(
        name = "com.breskeby.ideaindextracker.settings",
        storages = @Storage("IndexingStatsCollectorPlugin.xml")
)
public class IscSettingsState implements PersistentStateComponent<IscSettingsState> {


    // TODO Add customizable es index name
    public String elasticsearchHost = "";
    public int elasticsearchPort = 9243;
    public boolean anonymize = true;

    public String elasticsearchUsername = "";
    public String elasticsearchPassword = "";

    public String elasticsearchAccessToken = "";
    public String elasticsearchApiKey = "";
    public String elasticsearchApiSecret = "";

    public AuthType authType = AuthType.NO_AUTH;

    public static IscSettingsState getInstance() {
        return ApplicationManager.getApplication().getService(IscSettingsState.class);
    }

    @Nullable
    @Override
    public IscSettingsState getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull IscSettingsState state) {
        XmlSerializerUtil.copyBean(state, this);
    }

    public enum AuthType {
        NO_AUTH,
        BASIC_AUTH,
        ACCESS_TOKEN_AUTH,
        API_KEYS_AUTH;
    }
}

