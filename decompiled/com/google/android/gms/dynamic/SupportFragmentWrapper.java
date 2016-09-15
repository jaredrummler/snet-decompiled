package com.google.android.gms.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import com.google.android.gms.dynamic.IFragmentWrapper.Stub;

public final class SupportFragmentWrapper extends Stub {
    private Fragment mFragment;

    public static SupportFragmentWrapper wrap(Fragment fragment) {
        return fragment != null ? new SupportFragmentWrapper(fragment) : null;
    }

    private SupportFragmentWrapper(Fragment fragment) {
        this.mFragment = fragment;
    }

    public IObjectWrapper getActivity() {
        return ObjectWrapper.wrap(this.mFragment.getActivity());
    }

    public Bundle getArguments() {
        return this.mFragment.getArguments();
    }

    public int getId() {
        return this.mFragment.getId();
    }

    public IFragmentWrapper getParentFragment() {
        return wrap(this.mFragment.getParentFragment());
    }

    public IObjectWrapper getResources() {
        return ObjectWrapper.wrap(this.mFragment.getResources());
    }

    public boolean getRetainInstance() {
        return this.mFragment.getRetainInstance();
    }

    public String getTag() {
        return this.mFragment.getTag();
    }

    public IFragmentWrapper getTargetFragment() {
        return wrap(this.mFragment.getTargetFragment());
    }

    public int getTargetRequestCode() {
        return this.mFragment.getTargetRequestCode();
    }

    public boolean getUserVisibleHint() {
        return this.mFragment.getUserVisibleHint();
    }

    public IObjectWrapper getView() {
        return ObjectWrapper.wrap(this.mFragment.getView());
    }

    public boolean isAdded() {
        return this.mFragment.isAdded();
    }

    public boolean isDetached() {
        return this.mFragment.isDetached();
    }

    public boolean isHidden() {
        return this.mFragment.isHidden();
    }

    public boolean isInLayout() {
        return this.mFragment.isInLayout();
    }

    public boolean isRemoving() {
        return this.mFragment.isRemoving();
    }

    public boolean isResumed() {
        return this.mFragment.isResumed();
    }

    public boolean isVisible() {
        return this.mFragment.isVisible();
    }

    public void registerForContextMenu(IObjectWrapper viewWrapper) {
        this.mFragment.registerForContextMenu((View) ObjectWrapper.unwrap(viewWrapper));
    }

    public void setHasOptionsMenu(boolean hasMenu) {
        this.mFragment.setHasOptionsMenu(hasMenu);
    }

    public void setMenuVisibility(boolean menuVisible) {
        this.mFragment.setMenuVisibility(menuVisible);
    }

    public void setRetainInstance(boolean retain) {
        this.mFragment.setRetainInstance(retain);
    }

    public void setUserVisibleHint(boolean isVisibleToUser) {
        this.mFragment.setUserVisibleHint(isVisibleToUser);
    }

    public void startActivity(Intent intent) {
        this.mFragment.startActivity(intent);
    }

    public void startActivityForResult(Intent intent, int requestCode) {
        this.mFragment.startActivityForResult(intent, requestCode);
    }

    public void unregisterForContextMenu(IObjectWrapper viewWrapper) {
        this.mFragment.unregisterForContextMenu((View) ObjectWrapper.unwrap(viewWrapper));
    }
}
