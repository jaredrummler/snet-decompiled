package com.google.android.gms.common.audience.dialogs;

import android.content.Intent;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.audience.dialogs.AclSelection.Builder;
import com.google.android.gms.common.audience.dialogs.CircleSelection.Results;
import com.google.android.gms.common.audience.dialogs.CircleSelection.SelectBuilder;
import com.google.android.gms.common.audience.dialogs.CircleSelection.UpdateBuilder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.common.people.data.Audience;
import com.google.android.gms.common.people.data.AudienceMember;
import com.google.android.gms.people.internal.PeopleUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AudienceSelectionIntentBuilder implements UpdateBuilder, SelectBuilder, Results, Builder, AclSelection.Results, FaclSelection.Builder, FaclSelection.Results {
    public static final int DOMAIN_RESTRICTED_NOT_VISIBLE = 0;
    public static final int DOMAIN_RESTRICTED_OFF = 2;
    public static final int DOMAIN_RESTRICTED_ON = 1;
    private static final String EXTRA_ACCOUNT_NAME = "com.google.android.gms.common.acl.EXTRA_ACCOUNT_NAME";
    private static final String EXTRA_ADDED_AUDIENCE = "com.google.android.gms.common.acl.EXTRA_ADDED_AUDIENCE";
    private static final String EXTRA_ALLOW_EMPTY_SELECTION = "ALLOW_EMPTY";
    private static final String EXTRA_ALL_CIRCLES_CHECKBOX_TEXT = "ALL_CIRCLES_CHECKBOX_TEXT";
    private static final String EXTRA_ALL_CIRCLES_CHECKED = "ALL_CIRCLES_CHECKED";
    private static final String EXTRA_ALL_CONTACTS_CHECKBOX_TEXT = "ALL_CONTACTS_CHECKBOX_TEXT";
    private static final String EXTRA_ALL_CONTACTS_CHECKED = "ALL_CONTACTS_CHECKED";
    private static final String EXTRA_CANCEL_TEXT = "CANCEL_TEXT";
    private static final String EXTRA_CLIENT_APPLICATION_ID = "com.google.android.gms.common.acl.EXTRA_CLIENT_APPLICATION_ID";
    private static final String EXTRA_DESCRIPTION_TEXT = "DESCRIPTION_TEXT";
    private static final String EXTRA_DOMAIN_RESTRICTED = "com.google.android.gms.common.acl.EXTRA_DOMAIN_RESTRICTED";
    private static final String EXTRA_HAS_SHOW_CIRCLES = "HAS_SHOW_CIRCLES";
    private static final String EXTRA_HEADER_BACKGROUND_COLOR = "com.google.android.gms.common.acl.EXTRA_HEADER_BACKGROUND_COLOR";
    private static final String EXTRA_HEADER_TEXT_COLOR = "com.google.android.gms.common.acl.EXTRA_HEADER_TEXT_COLOR";
    private static final String EXTRA_INCLUDE_SUGGESTIONS = "EXTRA_INCLUDE_SUGGESTIONS_WITH_PEOPLE";
    private static final String EXTRA_INITIAL_AUDIENCE = "com.google.android.gms.common.acl.EXTRA_INITIAL_AUDIENCE";
    private static final String EXTRA_KNOWN_AUDIENCE = "INITIAL_ACL";
    private static final String EXTRA_LOAD_CIRCLES = "LOAD_CIRCLES";
    private static final String EXTRA_LOAD_PEOPLE = "LOAD_PEOPLE";
    private static final String EXTRA_LOAD_PEOPLE_TYPE = "LOAD_PEOPLE_TYPE";
    private static final String EXTRA_MAX_SUGGESTED_DEVICE = "EXTRA_MAX_SUGGESTED_DEVICE";
    private static final String EXTRA_MAX_SUGGESTED_IMAGES = "EXTRA_MAX_SUGGESTED_IMAGES";
    private static final String EXTRA_MAX_SUGGESTED_LIST_ITEMS = "EXTRA_MAX_SUGGESTED_LIST_ITEMS";
    private static final String EXTRA_OK_TEXT = "OK_TEXT";
    private static final String EXTRA_PLUS_PAGE_ID = "com.google.android.gms.common.acl.EXTRA_PLUS_PAGE_ID";
    private static final String EXTRA_REMOVED_AUDIENCE = "com.google.android.gms.common.acl.EXTRA_REMOVED_AUDIENCE";
    private static final String EXTRA_SEARCH_DEVICE = "EXTRA_SEARCH_DEVICE";
    private static final String EXTRA_SEARCH_EMAIL = "EXTRA_SEARCH_EMAIL";
    private static final String EXTRA_SHOULD_LOAD_GROUPS = "SHOULD_LOAD_GROUPS";
    private static final String EXTRA_SHOULD_LOAD_SUGGESTED = "SHOULD_LOAD_SUGGESTED";
    private static final String EXTRA_SHOW_ALL_CONTACTS_CHECKBOX = "SHOW_ALL_CONTACTS_CHECKBOX";
    private static final String EXTRA_SHOW_CANCEL_VISIBLE = "SHOW_CANCEL_VISIBLE";
    private static final String EXTRA_SHOW_CHIPS_VISIBLE = "SHOW_CHIPS_VISIBLE";
    private static final String EXTRA_SHOW_CIRCLE_SELECTION = "SHOW_ALL_CIRCLES_CHECKBOX";
    private static final String EXTRA_SHOW_HIDDEN_CIRCLES_TEXT = "SHOW_HIDDEN_CIRCLES_TEXT";
    private static final String EXTRA_SHOW_SECTION_TITLES = "SHOW_SECTION_TITLES";
    private static final String EXTRA_TITLE_LOGO = "TITLE_LOGO";
    private static final String EXTRA_TITLE_TEXT = "com.google.android.gms.common.acl.EXTRA_TITLE_TEXT";
    private static final String EXTRA_UPDATE_PERSON = "com.google.android.gms.common.acl.EXTRA_UPDATE_PERSON";
    private static final String EXTRA_UPDATE_PERSON_ID = "com.google.android.gms.common.acl.EXTRA_UPDATE_PERSON_ID";
    private final Intent mIntent;

    public AudienceSelectionIntentBuilder(String action) {
        this(new Intent(action).setPackage(GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_PACKAGE));
    }

    public AudienceSelectionIntentBuilder(Intent intent) {
        this.mIntent = new Intent(intent);
    }

    public Intent build() {
        return this.mIntent;
    }

    public AudienceSelectionIntentBuilder setAccountName(String accountName) {
        this.mIntent.putExtra(EXTRA_ACCOUNT_NAME, accountName);
        return this;
    }

    public AudienceSelectionIntentBuilder setPlusPageId(String plusPageId) {
        this.mIntent.putExtra(EXTRA_PLUS_PAGE_ID, plusPageId);
        return this;
    }

    public AudienceSelectionIntentBuilder setClientApplicationId(String clientApplicationId) {
        this.mIntent.putExtra(EXTRA_CLIENT_APPLICATION_ID, clientApplicationId);
        return this;
    }

    public UpdateBuilder setHeaderTextColor(int color) {
        this.mIntent.putExtra(EXTRA_HEADER_TEXT_COLOR, color);
        return this;
    }

    public UpdateBuilder setHeaderBackgroundColor(int color) {
        this.mIntent.putExtra(EXTRA_HEADER_BACKGROUND_COLOR, color);
        return this;
    }

    @Deprecated
    public AudienceSelectionIntentBuilder setUpdatePersonId(String peopleQualifiedId) {
        PeopleUtils.checkQualifiedId(peopleQualifiedId, "People qualified ID");
        SafeParcelableSerializer.serializeToIntentExtra(AudienceMember.forPersonWithPeopleQualifiedId(peopleQualifiedId, null, null), this.mIntent, EXTRA_UPDATE_PERSON);
        return this;
    }

    public AudienceSelectionIntentBuilder setUpdatePerson(AudienceMember person) {
        SafeParcelableSerializer.serializeToIntentExtra(person, this.mIntent, EXTRA_UPDATE_PERSON);
        return this;
    }

    public AudienceSelectionIntentBuilder setTitleText(String title) {
        this.mIntent.putExtra(EXTRA_TITLE_TEXT, title);
        return this;
    }

    public AudienceSelectionIntentBuilder setDomainRestricted(int flag) {
        this.mIntent.putExtra(EXTRA_DOMAIN_RESTRICTED, flag);
        return this;
    }

    public AudienceSelectionIntentBuilder setKnownAudienceMembers(List<AudienceMember> audience) {
        this.mIntent.putParcelableArrayListExtra(EXTRA_KNOWN_AUDIENCE, toArrayList(audience));
        return this;
    }

    public AudienceSelectionIntentBuilder setAllCirclesChecked(boolean checked) {
        this.mIntent.putExtra(EXTRA_ALL_CIRCLES_CHECKED, checked);
        return this;
    }

    public AudienceSelectionIntentBuilder setAllContactsChecked(boolean checked) {
        this.mIntent.putExtra(EXTRA_ALL_CONTACTS_CHECKED, checked);
        return this;
    }

    public AudienceSelectionIntentBuilder setTitleLogo(String title) {
        this.mIntent.putExtra(EXTRA_TITLE_LOGO, title);
        return this;
    }

    public AudienceSelectionIntentBuilder setInitialCircles(List<AudienceMember> circles) {
        setInitialAudience((List) circles);
        return this;
    }

    public AudienceSelectionIntentBuilder setInitialAcl(List<AudienceMember> acl) {
        setInitialAudience((List) acl);
        return this;
    }

    public AudienceSelectionIntentBuilder setInitialAcl(Audience audience) {
        setInitialAudience(audience);
        return this;
    }

    public boolean getAllCirclesChecked() {
        return getAllCirclesChecked(this.mIntent, false);
    }

    public boolean getAllContactsChecked() {
        return getAllContactsChecked(this.mIntent, false);
    }

    public AudienceMember getUpdatePerson() {
        return getUpdatePerson(this.mIntent);
    }

    public ArrayList<AudienceMember> getAddedAudienceDelta() {
        return getAddedAudienceDelta(this.mIntent);
    }

    public ArrayList<AudienceMember> getRemovedAudienceDelta() {
        return getRemovedAudienceDelta(this.mIntent);
    }

    public List<AudienceMember> getInitialAudienceMembers() {
        return getInitialAudienceMembers(this.mIntent);
    }

    public ArrayList<AudienceMember> getSelectedAudienceMembers() {
        return getSelectedAudienceMembers(this.mIntent);
    }

    public ArrayList<AudienceMember> getAddedCirclesDelta() {
        return getAddedAudienceDelta();
    }

    public ArrayList<AudienceMember> getRemovedCirclesDelta() {
        return getRemovedAudienceDelta();
    }

    public List<AudienceMember> getInitialCircles() {
        return getInitialAudienceMembers();
    }

    public ArrayList<AudienceMember> getSelectedCircles() {
        return getSelectedAudienceMembers();
    }

    public int getDomainRestricted() {
        return getDomainRestricted(this.mIntent, DOMAIN_RESTRICTED_NOT_VISIBLE);
    }

    public AudienceSelectionIntentBuilder setLoadPeople(boolean loadPeople) {
        this.mIntent.putExtra(EXTRA_LOAD_PEOPLE, loadPeople);
        return this;
    }

    public AudienceSelectionIntentBuilder setLoadPeopleType(int peopleType) {
        this.mIntent.putExtra(EXTRA_LOAD_PEOPLE_TYPE, peopleType);
        return this;
    }

    public AudienceSelectionIntentBuilder setLoadCircles(boolean loadCircles) {
        this.mIntent.putExtra(EXTRA_LOAD_CIRCLES, loadCircles);
        return this;
    }

    public AudienceSelectionIntentBuilder setLoadSystemGroups(boolean loadGroups) {
        this.mIntent.putExtra(EXTRA_SHOULD_LOAD_GROUPS, loadGroups);
        return this;
    }

    public AudienceSelectionIntentBuilder setLoadSuggested(boolean loadSuggested) {
        this.mIntent.putExtra(EXTRA_SHOULD_LOAD_SUGGESTED, loadSuggested);
        return this;
    }

    public AudienceSelectionIntentBuilder setIncludeSuggestionsWithPeople(boolean includeSuggestions) {
        this.mIntent.putExtra(EXTRA_INCLUDE_SUGGESTIONS, includeSuggestions);
        return this;
    }

    public AudienceSelectionIntentBuilder setMaxSuggestedImages(int maxSuggestedImages) {
        this.mIntent.putExtra(EXTRA_MAX_SUGGESTED_IMAGES, maxSuggestedImages);
        return this;
    }

    public AudienceSelectionIntentBuilder setMaxSuggestedListItems(int maxSuggestedListItems) {
        this.mIntent.putExtra(EXTRA_MAX_SUGGESTED_LIST_ITEMS, maxSuggestedListItems);
        return this;
    }

    public AudienceSelectionIntentBuilder setMaxSuggestedDeviceContacts(int maxSuggestedDevice) {
        this.mIntent.putExtra(EXTRA_MAX_SUGGESTED_DEVICE, maxSuggestedDevice);
        return this;
    }

    public AudienceSelectionIntentBuilder setSearchDeviceContacts(boolean searchDeviceContacts) {
        this.mIntent.putExtra(EXTRA_SEARCH_DEVICE, searchDeviceContacts);
        return this;
    }

    public AudienceSelectionIntentBuilder setSearchEmail(boolean searchEmail) {
        this.mIntent.putExtra(EXTRA_SEARCH_EMAIL, searchEmail);
        return this;
    }

    public AudienceSelectionIntentBuilder setShowSectionTitles(boolean showSectionTitles) {
        this.mIntent.putExtra(EXTRA_SHOW_SECTION_TITLES, showSectionTitles);
        return this;
    }

    public AudienceSelectionIntentBuilder setDescription(String description) {
        this.mIntent.putExtra(EXTRA_DESCRIPTION_TEXT, description);
        return this;
    }

    public AudienceSelectionIntentBuilder setCancelText(String cancelText) {
        this.mIntent.putExtra(EXTRA_CANCEL_TEXT, cancelText);
        return this;
    }

    public AudienceSelectionIntentBuilder setOkText(String okText) {
        this.mIntent.putExtra(EXTRA_OK_TEXT, okText);
        return this;
    }

    public AudienceSelectionIntentBuilder setShowCancel(boolean cancelVisible) {
        this.mIntent.putExtra(EXTRA_SHOW_CANCEL_VISIBLE, cancelVisible);
        return this;
    }

    public AudienceSelectionIntentBuilder setShowChips(boolean chipsVisible) {
        this.mIntent.putExtra(EXTRA_SHOW_CHIPS_VISIBLE, chipsVisible);
        return this;
    }

    public AudienceSelectionIntentBuilder setShowHiddenCirclesText(boolean showText) {
        this.mIntent.putExtra(EXTRA_SHOW_HIDDEN_CIRCLES_TEXT, showText);
        return this;
    }

    public AudienceSelectionIntentBuilder setHasShowCircles(boolean has) {
        this.mIntent.putExtra(EXTRA_HAS_SHOW_CIRCLES, has);
        return this;
    }

    public AudienceSelectionIntentBuilder setShowCircles(boolean show) {
        this.mIntent.putExtra(EXTRA_SHOW_CIRCLE_SELECTION, show);
        return this;
    }

    public AudienceSelectionIntentBuilder setShowContacts(boolean show) {
        this.mIntent.putExtra(EXTRA_SHOW_ALL_CONTACTS_CHECKBOX, show);
        return this;
    }

    public AudienceSelectionIntentBuilder setAllowEmptySelection(boolean allowed) {
        this.mIntent.putExtra(EXTRA_ALLOW_EMPTY_SELECTION, allowed);
        return this;
    }

    public AudienceSelectionIntentBuilder setAllCirclesCheckboxText(String allCirclesCheckBoxText) {
        this.mIntent.putExtra(EXTRA_ALL_CIRCLES_CHECKBOX_TEXT, allCirclesCheckBoxText);
        return this;
    }

    public AudienceSelectionIntentBuilder setAllContactsCheckboxText(String text) {
        this.mIntent.putExtra(EXTRA_ALL_CONTACTS_CHECKBOX_TEXT, text);
        return this;
    }

    public AudienceSelectionIntentBuilder setInitialAudience(List<AudienceMember> audience) {
        if (audience == null) {
            audience = Collections.EMPTY_LIST;
        }
        this.mIntent.putParcelableArrayListExtra(EXTRA_INITIAL_AUDIENCE, toArrayList(audience));
        return this;
    }

    public AudienceSelectionIntentBuilder setInitialAudience(Audience audience) {
        setInitialAudience(audience.getAudienceMemberList());
        return this;
    }

    public AudienceSelectionIntentBuilder setAddedAudienceDelta(ArrayList<AudienceMember> audience) {
        this.mIntent.putParcelableArrayListExtra(EXTRA_ADDED_AUDIENCE, audience);
        return this;
    }

    public AudienceSelectionIntentBuilder setRemovedAudienceDelta(ArrayList<AudienceMember> audience) {
        this.mIntent.putParcelableArrayListExtra(EXTRA_REMOVED_AUDIENCE, audience);
        return this;
    }

    public static boolean getAllowEmptySelection(Intent intent, boolean defaultValue) {
        return intent.getBooleanExtra(EXTRA_ALLOW_EMPTY_SELECTION, defaultValue);
    }

    public static String getDescription(Intent intent) {
        return intent.getStringExtra(EXTRA_DESCRIPTION_TEXT);
    }

    public static String getOkText(Intent intent) {
        return intent.getStringExtra(EXTRA_OK_TEXT);
    }

    public static ArrayList<AudienceMember> getKnownAudienceMembers(Intent intent) {
        return intent.getParcelableArrayListExtra(EXTRA_KNOWN_AUDIENCE);
    }

    public static boolean getLoadCircles(Intent intent, boolean defaultValue) {
        return intent.getBooleanExtra(EXTRA_LOAD_CIRCLES, defaultValue);
    }

    public static boolean getLoadSystemGroups(Intent intent, boolean defaultValue) {
        return intent.getBooleanExtra(EXTRA_SHOULD_LOAD_GROUPS, defaultValue);
    }

    public static boolean getLoadSuggested(Intent intent, boolean defaultValue) {
        return intent.getBooleanExtra(EXTRA_SHOULD_LOAD_SUGGESTED, defaultValue);
    }

    public static boolean getIncludeSuggestionsWithPeople(Intent intent, boolean defaultValue) {
        return intent.getBooleanExtra(EXTRA_INCLUDE_SUGGESTIONS, defaultValue);
    }

    public static int getMaxSuggestedImages(Intent intent, int defaultValue) {
        return intent.getIntExtra(EXTRA_MAX_SUGGESTED_IMAGES, defaultValue);
    }

    public static int getMaxSuggestedListItems(Intent intent, int defaultValue) {
        return intent.getIntExtra(EXTRA_MAX_SUGGESTED_LIST_ITEMS, defaultValue);
    }

    public static int getMaxSuggestedDeviceContacts(Intent intent, int defaultValue) {
        return intent.getIntExtra(EXTRA_MAX_SUGGESTED_DEVICE, defaultValue);
    }

    public static boolean getSearchDeviceContacts(Intent intent, boolean defaultValue) {
        return intent.getBooleanExtra(EXTRA_SEARCH_DEVICE, defaultValue);
    }

    public static boolean getSearchEmail(Intent intent, boolean defaultValue) {
        return intent.getBooleanExtra(EXTRA_SEARCH_EMAIL, defaultValue);
    }

    public static String getAccountName(Intent intent) {
        return intent.getStringExtra(EXTRA_ACCOUNT_NAME);
    }

    public static String getPlusPageId(Intent intent) {
        return intent.getStringExtra(EXTRA_PLUS_PAGE_ID);
    }

    public static String getClientApplicationId(Intent intent) {
        return intent.getStringExtra(EXTRA_CLIENT_APPLICATION_ID);
    }

    public static AudienceMember getUpdatePerson(Intent intent) {
        if (intent.hasExtra(EXTRA_UPDATE_PERSON)) {
            return (AudienceMember) SafeParcelableSerializer.deserializeFromIntentExtra(intent, EXTRA_UPDATE_PERSON, AudienceMember.CREATOR);
        }
        if (intent.hasExtra(EXTRA_UPDATE_PERSON_ID)) {
            return AudienceMember.forPersonWithPeopleQualifiedId(intent.getStringExtra(EXTRA_UPDATE_PERSON_ID), null, null);
        }
        return null;
    }

    public static String getUpdatePersonId(Intent intent) {
        if (intent.hasExtra(EXTRA_UPDATE_PERSON) || intent.hasExtra(EXTRA_UPDATE_PERSON_ID)) {
            return getUpdatePerson(intent).getPeopleQualifiedId();
        }
        return null;
    }

    public static boolean getLoadPeople(Intent intent, boolean defaultValue) {
        return intent.getBooleanExtra(EXTRA_LOAD_PEOPLE, defaultValue);
    }

    public static int getLoadPeopleType(Intent intent, int defaultValue) {
        return intent.getIntExtra(EXTRA_LOAD_PEOPLE_TYPE, defaultValue);
    }

    public static String getTitle(Intent intent) {
        return intent.getStringExtra(EXTRA_TITLE_TEXT);
    }

    public static int getDomainRestricted(Intent intent, int defaultValue) {
        return intent.getIntExtra(EXTRA_DOMAIN_RESTRICTED, defaultValue);
    }

    public static int getHeaderTextColor(Intent intent, int defaultColor) {
        return intent.getIntExtra(EXTRA_HEADER_TEXT_COLOR, defaultColor);
    }

    public static int getHeaderBackgroundColor(Intent intent, int defaultColor) {
        return intent.getIntExtra(EXTRA_HEADER_BACKGROUND_COLOR, defaultColor);
    }

    public static boolean shouldShowSectionTitles(Intent intent, boolean defaultValue) {
        return intent.getBooleanExtra(EXTRA_SHOW_SECTION_TITLES, defaultValue);
    }

    public static boolean getShowChips(Intent intent, boolean defaultValue) {
        return intent.getBooleanExtra(EXTRA_SHOW_CHIPS_VISIBLE, defaultValue);
    }

    public static boolean getShowCancel(Intent intent, boolean defaultValue) {
        return intent.getBooleanExtra(EXTRA_SHOW_CANCEL_VISIBLE, defaultValue);
    }

    public static boolean getShowHiddenCirclesText(Intent intent, boolean defaultValue) {
        return intent.getBooleanExtra(EXTRA_SHOW_HIDDEN_CIRCLES_TEXT, defaultValue);
    }

    public static String getAllCirclesCheckboxText(Intent intent) {
        return intent.getStringExtra(EXTRA_ALL_CIRCLES_CHECKBOX_TEXT);
    }

    public static boolean getAllCirclesChecked(Intent intent, boolean defaultValue) {
        return intent.getBooleanExtra(EXTRA_ALL_CIRCLES_CHECKED, defaultValue);
    }

    public static boolean getHasShowCircles(Intent intent, boolean defaultValue) {
        return intent.getBooleanExtra(EXTRA_HAS_SHOW_CIRCLES, defaultValue);
    }

    public static boolean getShowCircles(Intent intent, boolean defaultValue) {
        return intent.getBooleanExtra(EXTRA_SHOW_CIRCLE_SELECTION, defaultValue);
    }

    public static boolean getShowContacts(Intent intent, boolean defaultValue) {
        return intent.getBooleanExtra(EXTRA_SHOW_ALL_CONTACTS_CHECKBOX, defaultValue);
    }

    public static String getAllContactsCheckboxText(Intent intent) {
        return intent.getStringExtra(EXTRA_ALL_CONTACTS_CHECKBOX_TEXT);
    }

    public static boolean getAllContactsChecked(Intent intent, boolean defaultValue) {
        return intent.getBooleanExtra(EXTRA_ALL_CONTACTS_CHECKED, defaultValue);
    }

    public static String getTitleLogo(Intent intent) {
        return intent.getStringExtra(EXTRA_TITLE_LOGO);
    }

    public static List<AudienceMember> getInitialAudienceMembers(Intent intent) {
        if (intent.hasExtra(EXTRA_INITIAL_AUDIENCE)) {
            return intent.getParcelableArrayListExtra(EXTRA_INITIAL_AUDIENCE);
        }
        return Collections.emptyList();
    }

    public static ArrayList<AudienceMember> getAddedAudienceDelta(Intent intent) {
        return intent.getParcelableArrayListExtra(EXTRA_ADDED_AUDIENCE);
    }

    public static ArrayList<AudienceMember> getRemovedAudienceDelta(Intent intent) {
        return intent.getParcelableArrayListExtra(EXTRA_REMOVED_AUDIENCE);
    }

    public static ArrayList<AudienceMember> getSelectedAudienceMembers(Intent intent) {
        ArrayList<AudienceMember> selectedAudience = new ArrayList();
        List<AudienceMember> initialAudience = getInitialAudienceMembers(intent);
        if (!(initialAudience == null || initialAudience.isEmpty())) {
            selectedAudience.addAll(initialAudience);
        }
        ArrayList<AudienceMember> removed = getRemovedAudienceDelta(intent);
        if (removed != null) {
            selectedAudience.removeAll(removed);
        }
        ArrayList<AudienceMember> added = getAddedAudienceDelta(intent);
        if (added != null) {
            selectedAudience.addAll(added);
        }
        return selectedAudience;
    }

    public static String getCancelText(Intent intent) {
        return intent.getStringExtra(EXTRA_CANCEL_TEXT);
    }

    private static <T> ArrayList<T> toArrayList(List<T> data) {
        if (data instanceof ArrayList) {
            return (ArrayList) data;
        }
        return new ArrayList(data);
    }
}
