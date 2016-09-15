package com.google.android.gms.people.identity.internal.models;

import com.google.android.gms.common.server.response.FastJsonResponse;
import com.google.android.gms.common.server.response.FastJsonResponse.Field;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class DefaultPersonListImpl extends FastJsonResponse {
    public static final String ITEMS = "items";
    private static final HashMap<String, Field<?, ?>> sFields;
    final Set<Integer> mIndicatorSet;
    List<DefaultPersonImpl> mItems;

    static {
        sFields = new HashMap();
        sFields.put(ITEMS, Field.forConcreteTypeArray(ITEMS, 2, DefaultPersonImpl.class));
    }

    public HashMap<String, Field<?, ?>> getFieldMappings() {
        return sFields;
    }

    public DefaultPersonListImpl() {
        this.mIndicatorSet = new HashSet();
    }

    public DefaultPersonListImpl(Set<Integer> indicatorSet, List<DefaultPersonImpl> items) {
        this.mIndicatorSet = indicatorSet;
        this.mItems = items;
    }

    public List<DefaultPersonImpl> getItems() {
        return this.mItems;
    }

    public boolean hasItems() {
        return this.mIndicatorSet.contains(Integer.valueOf(2));
    }

    public DefaultPersonListImpl setItems(List<DefaultPersonImpl> newValue) {
        this.mItems = newValue;
        this.mIndicatorSet.add(Integer.valueOf(2));
        return this;
    }

    public DefaultPersonListImpl addItems(DefaultPersonImpl newValue) {
        if (this.mItems == null) {
            this.mItems = new ArrayList();
        }
        this.mItems.add(newValue);
        this.mIndicatorSet.add(Integer.valueOf(2));
        return this;
    }

    protected Object getValueObject(String key) {
        return null;
    }

    protected boolean isPrimitiveFieldSet(String outputField) {
        return false;
    }

    protected boolean isFieldSet(Field field) {
        return this.mIndicatorSet.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
    }

    protected Object getFieldValue(Field field) {
        switch (field.getSafeParcelableFieldId()) {
            case Type.INDEFINITELY /*2*/:
                return this.mItems;
            default:
                throw new IllegalStateException("Unknown safe parcelable id=" + field.getSafeParcelableFieldId());
        }
    }

    protected void setStringInternal(Field<?, ?> field, String outputField, String value) {
        throw new IllegalArgumentException("Field with id=" + field.getSafeParcelableFieldId() + " is not known to be a String.");
    }

    public <T extends FastJsonResponse> void addConcreteTypeInternal(Field<?, ?> field, String outputField, T value) {
        throw new IllegalArgumentException("Field with id=" + field.getSafeParcelableFieldId() + " is not a known" + " custom type.  Found " + value.getClass().getCanonicalName() + ".");
    }

    public <T extends FastJsonResponse> void addConcreteTypeArrayInternal(Field<?, ?> field, String outputField, ArrayList<T> value) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        switch (safeParcelableFieldId) {
            case Type.INDEFINITELY /*2*/:
                this.mItems = value;
                this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
            default:
                throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not a known array of" + " custom type.  Found " + value.getClass().getCanonicalName() + ".");
        }
    }

    public int hashCode() {
        int hash = 0;
        for (Field<?, ?> field : sFields.values()) {
            if (isFieldSet(field)) {
                hash = (hash + field.getSafeParcelableFieldId()) + getFieldValue(field).hashCode();
            }
        }
        return hash;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DefaultPersonImpl)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        DefaultPersonImpl entity = (DefaultPersonImpl) obj;
        for (Field<?, ?> field : sFields.values()) {
            if (isFieldSet(field)) {
                if (!entity.isFieldSet(field)) {
                    return false;
                }
                if (!getFieldValue(field).equals(entity.getFieldValue(field))) {
                    return false;
                }
            } else if (entity.isFieldSet(field)) {
                return false;
            }
        }
        return true;
    }
}
