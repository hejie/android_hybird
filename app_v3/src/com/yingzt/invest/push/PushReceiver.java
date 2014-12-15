package com.yingzt.invest.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.igexin.sdk.PushConsts;
import com.igexin.sdk.PushManager;
import com.yingzt.invest.YZTApplication;
import com.yingzt.invest.YZTUtils;
import com.yingzt.invest.activity.WebViewActivity1;
import com.yingzt.invest.activity.WebViewActivity4;

public class PushReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();
		YZTUtils.log(1, "onReceive() action=" + bundle.getInt("action"));
		switch (bundle.getInt(PushConsts.CMD_ACTION)) {

		case PushConsts.GET_MSG_DATA:
			// 获取透传数据
			// String appid = bundle.getString("appid");
			byte[] payload = bundle.getByteArray("payload");
			
			String taskid = bundle.getString("taskid");
			String messageid = bundle.getString("messageid");

			// smartPush第三方回执调用接口，actionid范围为90000-90999，可根据业务场景执行
			boolean result = PushManager.getInstance().sendFeedbackMessage(context, taskid, messageid, 90001);
			YZTUtils.log(1,("第三方回执接口调用" + (result ? "成功" : "失败")));
			
			if (payload != null) {
				String data = new String(payload);

				YZTUtils.log(1, "Got Payload:" + data);
				YZTApplication app=(YZTApplication)(context.getApplicationContext());
				app.put("notifactionData", data);
				//启动应用
				//intent.setClass(context, WebViewActivity1.class);
				//intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				//context.startActivity(intent);
				
			}
			break;
		case PushConsts.GET_CLIENTID:
			// 获取ClientID(CID)
			// 第三方应用需要将CID上传到第三方服务器，并且将当前用户帐号和CID进行关联，以便日后通过用户帐号查找CID进行消息推送
			String cid = bundle.getString("clientid");
			YZTUtils.log(1,"get clientid="+cid);
			
			break;
		case PushConsts.THIRDPART_FEEDBACK:
			String appid = bundle.getString("appid");
			//String taskid = bundle.getString("taskid");
			String actionid = bundle.getString("actionid");
			//String result = bundle.getString("result");
			long timestamp = bundle.getLong("timestamp");

			YZTUtils.log(1, "appid = " + appid);
			//Log.d("GetuiSdkDemo", "taskid = " + taskid);
			YZTUtils.log(1, "actionid = " + actionid);
			//Log.d("GetuiSdkDemo", "result = " + result);
			YZTUtils.log(1, "timestamp = " + timestamp);
			break;
		default:
			break;
		}
	}
}
