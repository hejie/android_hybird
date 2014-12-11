package com.yingzt.invest.receiver;


import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.tencent.android.tpush.XGPushBaseReceiver;
import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushRegisterResult;
import com.tencent.android.tpush.XGPushShowedResult;
import com.tencent.android.tpush.XGPushTextMessage;
import com.yingzt.invest.common.NotificationService;
import com.yingzt.invest.po.XGNotification;

public class MessageReceiver extends XGPushBaseReceiver {
	private Intent intent = new Intent("com.qq.xgdemo.activity.UPDATE_LISTVIEW");
	public static final String LogTag = "TPushReceiver";

	private void show(Context context, String text) {
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}

	// ֪ͨչʾ
	@Override
	public void onNotifactionShowedResult(Context context,
			XGPushShowedResult notifiShowedRlt) {
		if (context == null || notifiShowedRlt == null) {
			return;
		}
		XGNotification notific = new XGNotification();
		notific.setMsg_id(notifiShowedRlt.getMsgId());
		notific.setTitle(notifiShowedRlt.getTitle());
		notific.setContent(notifiShowedRlt.getContent());
		// notificationActionType==1ΪActivity��2Ϊurl��3Ϊintent
		notific.setNotificationActionType(notifiShowedRlt
				.getNotificationActionType());
		// Activity,url,intent������ͨ��getActivity()���
		notific.setActivity(notifiShowedRlt.getActivity());
		notific.setUpdate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(Calendar.getInstance().getTime()));
		NotificationService.getInstance(context).save(notific);
		context.sendBroadcast(intent);
		show(context, "����1������Ϣ, " + "֪ͨ��չʾ �� " + notifiShowedRlt.toString());
	}

	@Override
	public void onUnregisterResult(Context context, int errorCode) {
		if (context == null) {
			return;
		}
		String text = null;
		if (errorCode == XGPushBaseReceiver.SUCCESS) {
			text = "��ע��ɹ�";
		} else {
			text = "��ע��ʧ��" + errorCode;
		}
		Log.d(LogTag, text);
		show(context, text);

	}

	@Override
	public void onSetTagResult(Context context, int errorCode, String tagName) {
		if (context == null) {
			return;
		}
		String text = null;
		if (errorCode == XGPushBaseReceiver.SUCCESS) {
			text = "\"" + tagName + "\"���óɹ�";
		} else {
			text = "\"" + tagName + "\"����ʧ��,�����룺" + errorCode;
		}
		Log.d(LogTag, text);
		show(context, text);

	}

	@Override
	public void onDeleteTagResult(Context context, int errorCode, String tagName) {
		if (context == null) {
			return;
		}
		String text = null;
		if (errorCode == XGPushBaseReceiver.SUCCESS) {
			text = "\"" + tagName + "\"ɾ���ɹ�";
		} else {
			text = "\"" + tagName + "\"ɾ��ʧ��,�����룺" + errorCode;
		}
		Log.d(LogTag, text);
		show(context, text);

	}

	// ֪ͨ����ص� actionType=1Ϊ����Ϣ�������actionType=0Ϊ����Ϣ�����
	@Override
	public void onNotifactionClickedResult(Context context,
			XGPushClickedResult message) {
		if (context == null || message == null) {
			return;
		}
		String text = null;
		if (message.getActionType() == XGPushClickedResult.NOTIFACTION_CLICKED_TYPE) {
			// ֪ͨ��֪ͨ�������������������
			// APP�Լ�����������ض���
			// �������������activity��onResumeҲ�ܼ������뿴��3���������
			text = "֪ͨ���� :" + message;
		} else if (message.getActionType() == XGPushClickedResult.NOTIFACTION_DELETED_TYPE) {
			// ֪ͨ���������������
			// APP�Լ�����֪ͨ����������ض���
			text = "֪ͨ����� :" + message;
		}
		Toast.makeText(context, "�㲥���յ�֪ͨ�����:" + message.toString(),
				Toast.LENGTH_SHORT).show();
		// ��ȡ�Զ���key-value
		String customContent = message.getCustomContent();
		if (customContent != null && customContent.length() != 0) {
			try {
				JSONObject obj = new JSONObject(customContent);
				// key1Ϊǰ̨���õ�key
				if (!obj.isNull("key")) {
					String value = obj.getString("key");
					Log.d(LogTag, "get custom value:" + value);
				}
				// ...
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		// APP��������Ĺ��̡�����
		Log.d(LogTag, text);
		show(context, text);
	}

	@Override
	public void onRegisterResult(Context context, int errorCode,
			XGPushRegisterResult message) {
		// TODO Auto-generated method stub
		if (context == null || message == null) {
			return;
		}
		String text = null;
		if (errorCode == XGPushBaseReceiver.SUCCESS) {
			text = message + "ע��ɹ�";
			// ��������token
			String token = message.getToken();
		} else {
			text = message + "ע��ʧ�ܣ������룺" + errorCode;
		}
		Log.d(LogTag, text);
		show(context, text);
	}

	// ��Ϣ͸��
	@Override
	public void onTextMessage(Context context, XGPushTextMessage message) {
		// TODO Auto-generated method stub
		String text = "�յ���Ϣ:" + message.toString();
		// ��ȡ�Զ���key-value
		String customContent = message.getCustomContent();
		if (customContent != null && customContent.length() != 0) {
			try {
				JSONObject obj = new JSONObject(customContent);
				// key1Ϊǰ̨���õ�key
				if (!obj.isNull("key")) {
					String value = obj.getString("key");
					Log.d(LogTag, "get custom value:" + value);
				}
				// ...
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		// APP����������Ϣ�Ĺ���...
		Log.d(LogTag, text);
		show(context, text);
	}

}

