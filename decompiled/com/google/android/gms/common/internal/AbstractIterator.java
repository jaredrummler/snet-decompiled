package com.google.android.gms.common.internal;

import com.google.wireless.android.play.playlog.proto.UserLocation.LaunchSettingsEvent.Action;
import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class AbstractIterator<T> implements Iterator<T> {
    private static final int STATE_DONE = 2;
    private static final int STATE_FAILED = 3;
    private static final int STATE_NOT_READY = 1;
    private static final int STATE_READY = 0;
    private T next;
    private int state;

    protected abstract T computeNext();

    protected AbstractIterator() {
        this.state = STATE_NOT_READY;
    }

    protected final T endOfData() {
        this.state = STATE_DONE;
        return null;
    }

    public final boolean hasNext() {
        Preconditions.checkState(this.state != STATE_FAILED);
        switch (this.state) {
            case Action.UNKNOWN /*0*/:
                return true;
            case STATE_DONE /*2*/:
                return false;
            default:
                return tryToComputeNext();
        }
    }

    private boolean tryToComputeNext() {
        this.state = STATE_FAILED;
        this.next = computeNext();
        if (this.state == STATE_DONE) {
            return false;
        }
        this.state = 0;
        return true;
    }

    public final T next() {
        if (hasNext()) {
            this.state = STATE_NOT_READY;
            return this.next;
        }
        throw new NoSuchElementException();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
