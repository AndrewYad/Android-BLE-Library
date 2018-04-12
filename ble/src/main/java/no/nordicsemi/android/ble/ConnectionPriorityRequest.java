package no.nordicsemi.android.ble;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

import no.nordicsemi.android.ble.callback.ConnectionPriorityCallback;
import no.nordicsemi.android.ble.callback.FailCallback;
import no.nordicsemi.android.ble.callback.SuccessCallback;

public class ConnectionPriorityRequest extends Request {
	private ConnectionPriorityCallback valueCallback;
	private final int value;

	ConnectionPriorityRequest(final @NonNull Type type, int priority) {
		super(type);
		if (priority < 0 || priority > 2)
			priority = 0; // Balanced
		this.value = priority;
	}

	@RequiresApi(api = Build.VERSION_CODES.O)
	@NonNull
	public ConnectionPriorityRequest then(final @NonNull ConnectionPriorityCallback callback) {
		this.valueCallback = callback;
		return this;
	}

	@Override
	@NonNull
	public ConnectionPriorityRequest done(final @NonNull SuccessCallback callback) {
		super.done(callback);
		return this;
	}

	@Override
	@NonNull
	public ConnectionPriorityRequest fail(final @NonNull FailCallback callback) {
		super.fail(callback);
		return this;
	}

	@RequiresApi(api = Build.VERSION_CODES.O)
	void notifyConnectionPriorityChanged(final int interval, final int latency, final int timeout) {
		if (valueCallback != null)
			valueCallback.onConnectionUpdated(interval, latency, timeout);
	}

	int getRequiredPriority() {
		return value;
	}
}