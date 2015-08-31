package com.fp.service.impl;

import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fp.action.tools.AlertEntity;
import com.fp.dao.VehicleDao;
import com.fp.domain.RestApiParamBean;
import com.fp.domain.RestLoginUserBean;
import com.fp.service.AzureService;
import com.fp.service.RestAPIService;
import com.fp.util.Constants;
import com.fp.util.RestfulMainHttpsUtil;

@Service
@Transactional
@Scope("prototype")
public class RestAPIServiceImpl implements RestAPIService {
	Logger logger = Logger.getLogger(RestAPIServiceImpl.class.getName());

	enum DoingTypeEnum {
		LOGIN, LOGIN1, LOCK, UNLOCK, REMOTE, CANCEL_REMOTE, REFRESH_STATUS, LOCK2, UNLOCK2, REMOTE2, CANCEL_REMOTE2, REFRESH_STATUS2, LOCK3, UNLOCK3, REMOTE3, CANCEL_REMOTE3, REFRESH_STATUS3, DISCONNECT_LOCK, DISCONNECT_UNLOCK, DISCONNECT_REMOTE, DISCONNECT_REFRESH_STATUS
	}

	private DoingTypeEnum doingType = DoingTypeEnum.LOGIN;

	@Override
	public void testAllInOne(final RestApiParamBean param) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				RestApiParamBean param2 = new RestApiParamBean();
				param2.setVin("2LMPJ9J91GBL20112");// ("2LMPJ9J91GBL20112");
				param2.setEsn("T51W0008");// ("T4CF0040");
//				param2.setUnameForTestLogin("pdtest5@pd.cn");
//				param2.setPasswordForTestLogin("12Qwaszx");
			param2.setUnameForTestLogin("zhang3@163.com");
			param2.setPasswordForTestLogin("Bbpp1314");
				param2.setUnameNotForTestLogin("pdtest4@pd.cn");
				param2.setPasswordNotForTestLogin("12Qwaszx");
				while(true) {
					loginUserAPIs(param2.getUnameForTestLogin(),
							param2.getPasswordForTestLogin(), param2);
					
					try {
						Thread.sleep(60000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		}).start();
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					// awakeOnly();
					// testTwoLogin();
					// testQA();
					// testQARealVehicle();
					// testQARealVehicleOneCircle();
					// testQARealVehicle_TDK_OneCircle();
					long intervalTime = 300000;
					try {
						RestApiParamBean param2 = new RestApiParamBean();
						param2.setVin("2LMPJ9J91GBL20112");// ("2LMPJ9J91GBL20112");
						param2.setEsn("T51W0008");// ("T4CF0040");
						param2.setUnameForTestLogin("pdtest5@pd.cn");
						param2.setPasswordForTestLogin("12Qwaszx");
						param2.setUnameNotForTestLogin("pdtest4@pd.cn");
						param2.setPasswordNotForTestLogin("12Qwaszx");
						
						/*loginUserAPIs(param2.getUnameForTestLogin(),
								param2.getPasswordForTestLogin(), param2);*/
						/*Thread.sleep(intervalTime);*/
						/*lockAPIs(true, param2);
						Thread.sleep(intervalTime);
						unlockAPIs(true, param2);
						Thread.sleep(intervalTime);
						Calendar cal = Calendar.getInstance();
						int hour = cal.get(Calendar.HOUR_OF_DAY);
						if (hour >= 8 && hour <=20) {
							remoteStartAlsoCancelStartAPIs(true, param2);
							Thread.sleep(intervalTime);
						}*/
						refreshVehicleAPIs(true, param2);
						Thread.sleep(intervalTime);

						/*RestApiParamBean param = new RestApiParamBean();
						param.setVin("5LMCJ2A9XFUJ12205");// ("2LMPJ9J91GBL20112");
						param.setEsn("T4860029");// ("T4CF0040");
						param.setUnameForTestLogin("pdtest4@pd.cn");
						param.setPasswordForTestLogin("12Qwaszx");
						param.setUnameNotForTestLogin("pdtest8@pd.cn");
						param.setPasswordNotForTestLogin("12Qwaszx");

						lockAPIs(true, param);
						Thread.sleep(intervalTime);
						unlockAPIs(true, param);
						Thread.sleep(intervalTime);*/
						// remoteStartAlsoCancelStartAPIs(true, param);
						//refreshVehicleAPIs(true, param);
						//Thread.sleep(intervalTime);

					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		}).start();

	}

	private void testQARealVehicle() {
		RestApiParamBean param2 = new RestApiParamBean();
		param2.setVin("2LMPJ9J91GBL20112");// ("2LMPJ9J91GBL20112");
		param2.setEsn("T51W0008");// ("T4CF0040");
		param2.setUnameForTestLogin("pdtest4@pd.cn");
		param2.setPasswordForTestLogin("12Qwaszx");
		param2.setUnameNotForTestLogin("pdtest4@pd.cn");
		param2.setPasswordNotForTestLogin("12Qwaszx");
		loginUserAPIs(param2.getUnameForTestLogin(),
				param2.getPasswordForTestLogin(), param2);
		lockAPIs(true, param2);
		unlockAPIs(true, param2);
		remoteStartAlsoCancelStartAPIs(true, param2);
		refreshVehicleAPIs(true, param2);
	}

	private void testQATDK() {
		RestApiParamBean param = new RestApiParamBean();
		param.setVin("5LMCJ2ANXFUJ16493");// ("2LMPJ9J91GBL20112");
		param.setEsn("T4860034");// ("T4CF0040");
		param.setUnameForTestLogin("pdtest4@pd.cn");
		param.setPasswordForTestLogin("12Qwaszx");
		param.setUnameNotForTestLogin("zhang3@163.com");
		param.setPasswordNotForTestLogin("Bbpp1314");

		lockAPIs(true, param);
		unlockAPIs(true, param);
		// remoteStartAlsoCancelStartAPIs(true, param);
		refreshVehicleAPIs(true, param);
	}

	private void testQARealVehicleOneCircle() {
		RestApiParamBean param2 = new RestApiParamBean();
		param2.setVin("2LMPJ9J91GBL20112");// ("2LMPJ9J91GBL20112");
		param2.setEsn("T51W0008");// ("T4CF0040");
		param2.setUnameForTestLogin("pdtest8@pd.cn");
		param2.setPasswordForTestLogin("12Qwaszx");
		param2.setUnameNotForTestLogin("pdtest4@pd.cn");
		param2.setPasswordNotForTestLogin("12Qwaszx");

		if (doingType == DoingTypeEnum.LOGIN) {
			logger.info("loginUserAPIs(param.getUnameNotForTestLogin()_REST, param.getPasswordNotForTestLogin()_REST);"
					+ param2.toString());
			loginUserAPIs(param2.getUnameForTestLogin(),
					param2.getPasswordForTestLogin(), param2);
			doingType = DoingTypeEnum.LOCK2;
		} else if (doingType == DoingTypeEnum.LOCK2) {
			logger.info("lockAPIs start(isDisconnectedFlg)" + param2.toString());
			lockAPIs(true, param2);
			logger.info("lockAPIs end(isDisconnectedFlg)" + param2.toString());
			doingType = DoingTypeEnum.UNLOCK2;
		} else if (doingType == DoingTypeEnum.UNLOCK2) {
			logger.info("unlockAPIs start (isDisconnectedFlg);"
					+ param2.toString());
			unlockAPIs(true, param2);
			logger.info("unlockAPIs end (isDisconnectedFlg);"
					+ param2.toString());
			doingType = DoingTypeEnum.REMOTE2;
		} else if (doingType == DoingTypeEnum.REMOTE2) {
			logger.info("remoteStartAlsoCancelStartAPIs start (isDisconnectedFlg);"
					+ param2.toString());
			remoteStartAlsoCancelStartAPIs(true, param2);
			logger.info("remoteStartAlsoCancelStartAPIs end(isDisconnectedFlg);"
					+ param2.toString());
			doingType = DoingTypeEnum.REFRESH_STATUS2;
		} else if (doingType == DoingTypeEnum.REFRESH_STATUS2) {
			logger.info("refreshVehicleAPIs start (isDisconnectedFlg);"
					+ param2.toString());
			refreshVehicleAPIs(true, param2);
			logger.info("refreshVehicleAPIs end (isDisconnectedFlg);"
					+ param2.toString());
			doingType = DoingTypeEnum.LOGIN;
		}
	}

	private void testQARealVehicle_TDK_OneCircle() {
		RestApiParamBean param2 = new RestApiParamBean();
		param2.setVin("2LMPJ9J91GBL20112");// ("2LMPJ9J91GBL20112");
		param2.setEsn("T51W0008");// ("T4CF0040");
		param2.setUnameForTestLogin("pdtest8@pd.cn");
		param2.setPasswordForTestLogin("12Qwaszx");
		param2.setUnameNotForTestLogin("pdtest4@pd.cn");
		param2.setPasswordNotForTestLogin("12Qwaszx");

		RestApiParamBean param = new RestApiParamBean();
		param.setVin("5LMCJ2ANXFUJ16493");// ("2LMPJ9J91GBL20112");
		param.setEsn("T4860034");// ("T4CF0040");
		param.setUnameForTestLogin("pdtest4@pd.cn");
		param.setPasswordForTestLogin("12Qwaszx");
		param.setUnameNotForTestLogin("zhang3@163.com");
		param.setPasswordNotForTestLogin("Bbpp1314");

		if (doingType == DoingTypeEnum.LOGIN) {
			logger.info("loginUserAPIs(param.getUnameNotForTestLogin()_REST, param.getPasswordNotForTestLogin()_REST);"
					+ param2.toString());
			loginUserAPIs(param2.getUnameForTestLogin(),
					param2.getPasswordForTestLogin(), param2);
			doingType = DoingTypeEnum.LOCK2;
		} else if (doingType == DoingTypeEnum.LOCK2) {
			logger.info("lockAPIs start(isDisconnectedFlg)" + param2.toString());
			lockAPIs(true, param2);
			logger.info("lockAPIs end(isDisconnectedFlg)" + param2.toString());
			doingType = DoingTypeEnum.UNLOCK2;
		} else if (doingType == DoingTypeEnum.UNLOCK2) {
			logger.info("unlockAPIs start (isDisconnectedFlg);"
					+ param2.toString());
			unlockAPIs(true, param2);
			logger.info("unlockAPIs end (isDisconnectedFlg);"
					+ param2.toString());
			doingType = DoingTypeEnum.REMOTE2;
		} else if (doingType == DoingTypeEnum.REMOTE2) {
			logger.info("remoteStartAlsoCancelStartAPIs start (isDisconnectedFlg);"
					+ param2.toString());
			remoteStartAlsoCancelStartAPIs(true, param2);
			logger.info("remoteStartAlsoCancelStartAPIs end(isDisconnectedFlg);"
					+ param2.toString());
			doingType = DoingTypeEnum.REFRESH_STATUS2;
		} else if (doingType == DoingTypeEnum.REFRESH_STATUS2) {
			logger.info("refreshVehicleAPIs start (isDisconnectedFlg);"
					+ param2.toString());
			refreshVehicleAPIs(true, param2);
			logger.info("refreshVehicleAPIs end (isDisconnectedFlg);"
					+ param2.toString());
			doingType = DoingTypeEnum.LOGIN;
		}
	}

	private void testQA() {
		RestApiParamBean param2 = new RestApiParamBean();
		param2.setVin("5LMCJ2ANXFUJ16493");// ("2LMPJ9J91GBL20112");
		param2.setEsn("T4860029");// ("T4CF0040");
		param2.setUnameForTestLogin("pdtest4@pd.cn");
		param2.setPasswordForTestLogin("12Qwaszx");
		param2.setUnameNotForTestLogin("pdtest4@pd.cn");
		param2.setPasswordNotForTestLogin("12Qwaszx");

		refreshVehicleAPIs(true, param2);
	}

	private void testTwoLogin() {
		RestApiParamBean param1 = new RestApiParamBean();
		param1.setVin("5LMCJ2ANXFUJ16493");
		param1.setEsn("T4CF0052");

		param1.setUnameForTestLogin("zhang3@163.com");
		param1.setPasswordForTestLogin("Bbpp1314");
		param1.setUnameNotForTestLogin("pdtest4@pd.cn");
		param1.setPasswordNotForTestLogin("12Qwaszx");

		RestApiParamBean param2 = new RestApiParamBean();
		param2.setVin("2LMPJ8L90GBL20108");// ("2LMPJ9J91GBL20112");
		param2.setEsn("T4CF0052");// ("T4CF0040");
		param2.setUnameForTestLogin("pdtest4@pd.cn");
		param2.setPasswordForTestLogin("12Qwaszx");
		param2.setUnameNotForTestLogin("pdtest4@pd.cn");
		param2.setPasswordNotForTestLogin("12Qwaszx");
		refreshVehicleAPIs(true, param2);
		if (doingType == DoingTypeEnum.LOGIN) {
			loginUserAPIs(param1.getUnameForTestLogin(),
					param1.getPasswordForTestLogin(), param1);
			doingType = DoingTypeEnum.LOGIN1;
		} else if (doingType == DoingTypeEnum.LOGIN1) {
			doingType = DoingTypeEnum.REFRESH_STATUS2; // æå®ä¸ä¸ä¸ªæ§è¡çæä½
		} else if (doingType == DoingTypeEnum.REFRESH_STATUS2) {
			logger.info("refreshVehicleAPIs start (isDisconnectedFlg);"
					+ param2.toString());

			logger.info("refreshVehicleAPIs end (isDisconnectedFlg);"
					+ param2.toString());
			doingType = DoingTypeEnum.LOGIN; // æå®ä¸ä¸ä¸ªæ§è¡çæä½
		}
	}

	private void awakeOnly() {
		RestfulMainHttpsUtil.trustEveryone();
		RestApiParamBean param = new RestApiParamBean();
		param.setVin("5LMCJ2ANXFUJ16493");
		param.setEsn("T4CF0052");
		// param.setUnameForTestLogin(Constants.UNAME_REST);
		// param.setPasswordForTestLogin(Constants.PASSWORD_REST);

		param.setUnameForTestLogin("zhang3@163.com");
		param.setPasswordForTestLogin("Bbpp1314");
		param.setUnameNotForTestLogin("pdtest4@pd.cn");
		param.setPasswordNotForTestLogin("12Qwaszx");

		/*
		 * RestApiParamBean param1 = new RestApiParamBean();
		 * param1.setVin("5LMCJ2AN0FUJ10556"); param1.setEsn("T4860029");
		 * param1.setUnameForTestLogin(Constants.UNAME_REST);
		 * param1.setPasswordForTestLogin(Constants.PASSWORD_REST);
		 * param1.setUnameNotForTestLogin("zhang3@163.com");
		 * param1.setPasswordNotForTestLogin("Bbpp1314");
		 */

		RestApiParamBean param2 = new RestApiParamBean();
		param2.setVin("2LMPJ8L90GBL20108");// ("2LMPJ9J91GBL20112");
		param2.setEsn("T4CF0052");// ("T4CF0040");
		/*
		 * param2.setUnameForTestLogin("zhang3@163.com");
		 * param2.setPasswordForTestLogin("Bbpp1314");
		 */
		param2.setUnameNotForTestLogin("pdtest4@pd.cn");
		param2.setPasswordNotForTestLogin("12Qwaszx");

		if (doingType == DoingTypeEnum.LOGIN) {
			logger.info("loginUserAPIs(param.getUnameNotForTestLogin()_REST, param.getPasswordNotForTestLogin()_REST);"
					+ param2.toString());
			loginUserAPIs(param2.getUnameForTestLogin(),
					param2.getPasswordForTestLogin(), param2);
			doingType = DoingTypeEnum.LOCK2; // æå®ä¸ä¸ä¸ªæ§è¡çæä½
		} else if (doingType == DoingTypeEnum.LOCK2) {
			logger.info("lockAPIs start(isDisconnectedFlg)" + param2.toString());
			lockAPIs(true, param2);
			logger.info("lockAPIs end(isDisconnectedFlg)" + param2.toString());
			doingType = DoingTypeEnum.UNLOCK2; // æå®ä¸ä¸ä¸ªæ§è¡çæä½
		} else if (doingType == DoingTypeEnum.UNLOCK2) {
			logger.info("unlockAPIs start (isDisconnectedFlg);"
					+ param2.toString());
			unlockAPIs(true, param2);
			logger.info("unlockAPIs end (isDisconnectedFlg);"
					+ param2.toString());
			doingType = DoingTypeEnum.REFRESH_STATUS2; // æå®ä¸ä¸ä¸ªæ§è¡çæä½
		} else if (doingType == DoingTypeEnum.REFRESH_STATUS2) {
			logger.info("refreshVehicleAPIs start (isDisconnectedFlg);"
					+ param2.toString());
			refreshVehicleAPIs(true, param2);
			logger.info("refreshVehicleAPIs end (isDisconnectedFlg);"
					+ param2.toString());
			doingType = DoingTypeEnum.LOGIN; // æå®ä¸ä¸ä¸ªæ§è¡çæä½
		}

		/*
		 * if (doingType == DoingTypeEnum.LOCK2) {
		 * logger.info("lockAPIs start(isDisconnectedFlg)"+param2.toString());
		 * lockAPIs(true, param2);
		 * logger.info("lockAPIs end(isDisconnectedFlg)"+param2.toString());
		 * doingType = DoingTypeEnum.UNLOCK2; // æå®ä¸ä¸ä¸ªæ§è¡çæä½
		 * } else if (doingType == DoingTypeEnum.UNLOCK2) {
		 * logger.info("unlockAPIs start (isDisconnectedFlg);"
		 * +param2.toString()); unlockAPIs(true, param2);
		 * logger.info("unlockAPIs end (isDisconnectedFlg);"+param2.toString());
		 * doingType = DoingTypeEnum.REFRESH_STATUS2; //
		 * æå®ä¸ä¸ä¸ªæ§è¡çæä½ } else if (doingType ==
		 * DoingTypeEnum.REFRESH_STATUS2) {
		 * logger.info("refreshVehicleAPIs start (isDisconnectedFlg);"
		 * +param2.toString()); refreshVehicleAPIs(true, param2);
		 * logger.info("refreshVehicleAPIs end (isDisconnectedFlg);"
		 * +param2.toString()); doingType = DoingTypeEnum.LOCK2; //
		 * æå®ä¸ä¸ä¸ªæ§è¡çæä½ }
		 */

		/*
		 * try { boolean isDisconnectedFlg = false;
		 * 
		 * boolean isRealCarOnly = true; if (isRealCarOnly) {
		 * loginUserAPIs(param2.getUnameForTestLogin(),
		 * param2.getPasswordForTestLogin(), param2); if (doingType ==
		 * Enum.LOGIN) { logger.info(
		 * "loginUserAPIs(param.getUnameNotForTestLogin()_REST, param.getPasswordNotForTestLogin()_REST);"
		 * +param.toString()); loginUserAPIs(param2.getUnameForTestLogin(),
		 * param2.getPasswordForTestLogin(), param2); doingType =
		 * DoingTypeEnum.LOCK3; // æå®ä¸ä¸ä¸ªæ§è¡çæä½ } else if
		 * (doingType == DoingTypeEnum.LOCK3) {
		 * logger.info("lockAPIs(isDisconnectedFlg)"+param2.toString());
		 * lockAPIs(isDisconnectedFlg, param2); doingType =
		 * DoingTypeEnum.UNLOCK3; // æå®ä¸ä¸ä¸ªæ§è¡çæä½ } else if
		 * (doingType == DoingTypeEnum.UNLOCK3) {
		 * logger.info("unlockAPIs(isDisconnectedFlg);"+param2.toString());
		 * unlockAPIs(isDisconnectedFlg, param2); doingType =
		 * DoingTypeEnum.REMOTE3; // æå®ä¸ä¸ä¸ªæ§è¡çæä½ } else if
		 * (doingType == DoingTypeEnum.REMOTE3) {
		 * logger.info("remoteStartAlsoCancelStartAPIs(isDisconnectedFlg);"
		 * +param2.toString());
		 * remoteStartAlsoCancelStartAPIs(isDisconnectedFlg, param2); doingType
		 * = DoingTypeEnum.REFRESH_STATUS3; // æå®ä¸ä¸ä¸ªæ§è¡çæä½ }
		 * else if (doingType == DoingTypeEnum.REFRESH_STATUS3) {
		 * logger.info("refreshVehicleAPIs(isDisconnectedFlg);"
		 * +param2.toString()); refreshVehicleAPIs(isDisconnectedFlg, param2);
		 * doingType = DoingTypeEnum.LOGIN; // æå®ä¸ä¸ä¸ªæ§è¡çæä½ }
		 * } else { if (doingType == DoingTypeEnum.LOGIN) { logger.info(
		 * "loginUserAPIs start(param.getUnameNotForTestLogin()_REST, param.getPasswordNotForTestLogin()_REST);"
		 * +param.toString()); loginUserAPIs(param.getUnameForTestLogin(),
		 * param.getPasswordForTestLogin(), param);
		 * logger.info("loginUserAPIs end"); doingType = DoingTypeEnum.LOCK; //
		 * æå®ä¸ä¸ä¸ªæ§è¡çæä½ } else if (doingType ==
		 * DoingTypeEnum.LOCK) {
		 * logger.info("lockAPIs start (isDisconnectedFlg)"+param.toString());
		 * lockAPIs(isDisconnectedFlg, param);
		 * logger.info("lockAPIs end (isDisconnectedFlg)"+param.toString());
		 * doingType = DoingTypeEnum.UNLOCK; // æå®ä¸ä¸ä¸ªæ§è¡çæä½ }
		 * else if (doingType == DoingTypeEnum.UNLOCK) {
		 * logger.info("unlockAPIs start (isDisconnectedFlg);"
		 * +param.toString()); unlockAPIs(isDisconnectedFlg, param);
		 * logger.info("unlockAPIs end (isDisconnectedFlg);"+param.toString());
		 * doingType = DoingTypeEnum.REMOTE; // æå®ä¸ä¸ä¸ªæ§è¡çæä½ }
		 * else if (doingType == DoingTypeEnum.REMOTE) {
		 * logger.info("remoteStartAlsoCancelStartAPIs start(isDisconnectedFlg);"
		 * +param.toString()); remoteStartAlsoCancelStartAPIs(isDisconnectedFlg,
		 * param);
		 * logger.info("remoteStartAlsoCancelStartAPIs end(isDisconnectedFlg);"
		 * +param.toString()); doingType = DoingTypeEnum.REFRESH_STATUS; //
		 * æå®ä¸ä¸ä¸ªæ§è¡çæä½ } else if (doingType ==
		 * DoingTypeEnum.REFRESH_STATUS) {
		 * logger.info("refreshVehicleAPIs start(isDisconnectedFlg);"
		 * +param.toString()); refreshVehicleAPIs(isDisconnectedFlg, param);
		 * logger
		 * .info("refreshVehicleAPIs end(isDisconnectedFlg);"+param.toString());
		 * doingType = DoingTypeEnum.LOCK2; // æå®ä¸ä¸ä¸ªæ§è¡çæä½ }
		 * else if (doingType == DoingTypeEnum.LOCK2) {
		 * logger.info("lockAPIs start(isDisconnectedFlg)"+param2.toString());
		 * lockAPIs(isDisconnectedFlg, param2);
		 * logger.info("lockAPIs end(isDisconnectedFlg)"+param2.toString());
		 * doingType = DoingTypeEnum.UNLOCK2; // æå®ä¸ä¸ä¸ªæ§è¡çæä½
		 * } else if (doingType == DoingTypeEnum.UNLOCK2) {
		 * logger.info("unlockAPIs start (isDisconnectedFlg);"
		 * +param2.toString()); unlockAPIs(isDisconnectedFlg, param2);
		 * logger.info("unlockAPIs end (isDisconnectedFlg);"+param2.toString());
		 * doingType = DoingTypeEnum.REMOTE2; // æå®ä¸ä¸ä¸ªæ§è¡çæä½
		 * } else if (doingType == DoingTypeEnum.REMOTE2) {
		 * logger.info("remoteStartAlsoCancelStartAPIs start (isDisconnectedFlg);"
		 * +param2.toString());
		 * remoteStartAlsoCancelStartAPIs(isDisconnectedFlg, param2);
		 * logger.info
		 * ("remoteStartAlsoCancelStartAPIs end(isDisconnectedFlg);"+param2
		 * .toString()); doingType = DoingTypeEnum.REFRESH_STATUS2; //
		 * æå®ä¸ä¸ä¸ªæ§è¡çæä½ } else if (doingType ==
		 * DoingTypeEnum.REFRESH_STATUS2) {
		 * logger.info("refreshVehicleAPIs start (isDisconnectedFlg);"
		 * +param2.toString()); refreshVehicleAPIs(isDisconnectedFlg, param2);
		 * logger
		 * .info("refreshVehicleAPIs end (isDisconnectedFlg);"+param2.toString
		 * ()); doingType = DoingTypeEnum.LOGIN; //
		 * æå®ä¸ä¸ä¸ªæ§è¡çæä½ } }
		 * 
		 * } catch (Exception e) {
		 * 
		 * logger.info(getStackMsg(e)); }
		 */

	}

	private void sleepAndawake(RestApiParamBean param) {

		try {
			List<AlertEntity> alerts = service.getAzureAlerts(Constants.ESN,
					Constants.ACCOUNTNAME, Constants.ACCOUNTKEY);
			AlertEntity alert = alerts.get(alerts.size() - 1);
			boolean isConnectionAlert = alert.getOpCode().contains(
					"TCU_CONNECTION_STATUS_ALERT");
			boolean isDisconnectedFlg = false;
			if (isConnectionAlert) {
				isDisconnectedFlg = alert.getDecodedPayLoad().contains(
						"DISCONNECTED");
			}

			if (doingType == DoingTypeEnum.LOGIN) {
				logger.info("loginUserAPIs(param.getUnameNotForTestLogin()_REST, param.getPasswordNotForTestLogin()_REST);"
						+ param.toString());
				loginUserAPIs(param.getUnameForTestLogin(),
						param.getPasswordForTestLogin(), param);
				doingType = DoingTypeEnum.DISCONNECT_LOCK; // æå®ä¸ä¸ä¸ªæ§è¡çæä½
			} else if (doingType == DoingTypeEnum.DISCONNECT_LOCK) {
				if (isDisconnectedFlg) {
					lockAPIs(isDisconnectedFlg, param);
					doingType = DoingTypeEnum.DISCONNECT_UNLOCK; // æå®ä¸ä¸ä¸ªæ§è¡çæä½
				}
			} else if (doingType == DoingTypeEnum.DISCONNECT_UNLOCK) {
				if (isDisconnectedFlg) {
					unlockAPIs(isDisconnectedFlg, param);
					doingType = DoingTypeEnum.DISCONNECT_REMOTE; // æå®ä¸ä¸ä¸ªæ§è¡çæä½
				}
			} else if (doingType == DoingTypeEnum.DISCONNECT_REMOTE) {
				if (isDisconnectedFlg) {
					remoteStartAlsoCancelStartAPIs(isDisconnectedFlg, param);
					doingType = DoingTypeEnum.DISCONNECT_REFRESH_STATUS; // æå®ä¸ä¸ä¸ªæ§è¡çæä½
				}
			} else if (doingType == DoingTypeEnum.DISCONNECT_REFRESH_STATUS) {
				if (isDisconnectedFlg) {
					refreshVehicleAPIs(isDisconnectedFlg, param);
					doingType = DoingTypeEnum.LOGIN; // æå®ä¸ä¸ä¸ªæ§è¡çæä½
				}
			}
		} catch (Exception e) {
			logger.info(getStackMsg(e));
		}

	}

	@Override
	public void lock() {

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				List<AlertEntity> alerts = service.getAzureAlerts(
						Constants.ESN, Constants.ACCOUNTNAME,
						Constants.ACCOUNTKEY);
				AlertEntity alert = alerts.get(alerts.size() - 1);
				boolean isConnectionAlert = alert.getOpCode().contains(
						"TCU_CONNECTION_STATUS_ALERT");
				if (isConnectionAlert) {
					boolean isDisconnectedFlg = alert.getDecodedPayLoad()
							.contains("DISCONNECTED");
					if (isDisconnectedFlg) {
						// lockAPIs();
					}
				}
			}
		}, 1000, 60000);
	}

	@Override
	public void unlock() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				List<AlertEntity> alerts = service.getAzureAlerts(
						Constants.ESN, Constants.ACCOUNTNAME,
						Constants.ACCOUNTKEY);
				AlertEntity alert = alerts.get(alerts.size() - 1);
				boolean isConnectionAlert = alert.getOpCode().contains(
						"TCU_CONNECTION_STATUS_ALERT");
				if (isConnectionAlert) {
					boolean isDisconnectedFlg = alert.get__payload().contains(
							"DISCONNECTED");
					if (isDisconnectedFlg) {
						// unlockAPIs();
					}
				}
			}
		}, 1000, 60000);
	}

	@Override
	public void remoteStart() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				List<AlertEntity> alerts = service.getAzureAlerts(
						Constants.ESN, Constants.ACCOUNTNAME,
						Constants.ACCOUNTKEY);
				AlertEntity alert = alerts.get(alerts.size() - 1);
				boolean isConnectionAlert = alert.getOpCode().contains(
						"TCU_CONNECTION_STATUS_ALERT");
				if (isConnectionAlert) {
					boolean isDisconnectedFlg = alert.get__payload().contains(
							"DISCONNECTED");
					if (isDisconnectedFlg) {
						// remoteStartAPIs();
					}
				}
			}
		}, 1000, 60000);
	}

	@Override
	public void cancelRemoteStart() {
		// List<AlertEntity> alerts = service.getAzureAlerts(Constants.ESN,
		// Constants.ACCOUNTNAME, Constants.ACCOUNTKEY);
		// AlertEntity alert = alerts.get(alerts.size()-1);
		// boolean isConnectionAlert =
		// alert.getOpCode().contains("TCU_CONNECTION_STATUS_ALERT");
		// if (isConnectionAlert) {
		// boolean isDisconnectedFlg =
		// alert.get__payload().contains("DISCONNECTED");
		// if (isDisconnectedFlg) {
		// cancelStartAPIs();
		// }
		// }

	}

	@Override
	public void refreshVehicle() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				List<AlertEntity> alerts = service.getAzureAlerts(
						Constants.ESN, Constants.ACCOUNTNAME,
						Constants.ACCOUNTKEY);
				AlertEntity alert = alerts.get(alerts.size() - 1);
				boolean isConnectionAlert = alert.getOpCode().contains(
						"TCU_CONNECTION_STATUS_ALERT");
				if (isConnectionAlert) {
					boolean isDisconnectedFlg = alert.get__payload().contains(
							"DISCONNECTED");
					if (isDisconnectedFlg) {
						// refreshVehicleAPIs();
					}
				}
			}
		}, 1000, 60000);
	}

	@Autowired
	private AzureService service;

	public AzureService getService() {
		return service;
	}

	public void setService(AzureService service) {
		this.service = service;
	}

	@Autowired
	private VehicleDao dao;

	public VehicleDao getDao() {
		return dao;
	}

	public void setDao(VehicleDao dao) {
		this.dao = dao;
	}

	private void loginUserAPIs(String name, String psw, RestApiParamBean param) {

		RestLoginUserBean bean = new RestLoginUserBean();
		bean.setExeParam(param.toString());
		StringBuffer errMsg = new StringBuffer();
		try {
			Date startDate = new Date();
			String loginLHStr = RestfulMainHttpsUtil.loginLightHouse(
					Constants.LIGHT_HOUSE_URL, name, psw);
			Date lhEndDate = new Date();
			// System.out.println(loginLHStr);
			// System.out
			// .println(" light house cost time: "
			// + ((lhEndDate.getTime() - startDate
			// .getTime()) / 1000)
			// + "."
			// + ((lhEndDate.getTime() - startDate
			// .getTime()) % 1000) + "s.");

			String lightHouseCost = ((lhEndDate.getTime() - startDate.getTime()) / 1000)
					+ "."
					+ String.format(
							"%1$,03d",
							((lhEndDate.getTime() - startDate.getTime()) % 1000));
			errMsg.append(loginLHStr + "\n");
			String lhToken = new JSONObject(loginLHStr)
					.getString("access_token");
			// System.out.println(lhToken);
			Date sdnStartDate = new Date();

			// Step 1 : Login User
			String loginResultJson = RestfulMainHttpsUtil.loginUser(lhToken);
			// System.out.println(loginResultJson);
			Date sdnEndDate = new Date();
			errMsg.append(loginResultJson + "\n");
			// System.out
			// .println(" sdn cost time: "
			// + ((sdnEndDate.getTime() - sdnStartDate
			// .getTime()) / 1000)
			// + "."
			// + ((sdnEndDate.getTime() - sdnStartDate
			// .getTime()) % 1000) + "s.");

			String sdnLoginUserCost = ((sdnEndDate.getTime() - sdnStartDate
					.getTime()) / 1000)
					+ "."
					+ String.format(
							"%1$,03d",
							((sdnEndDate.getTime() - sdnStartDate.getTime()) % 1000));

			String authToken = new JSONObject(loginResultJson)
					.getString("authToken");

			Date sdnGetProfileStartDate = new Date();
			String userProfileJson = RestfulMainHttpsUtil.getUserProfile(
					authToken, name, "12-21-2014%2010%3A01%3A50%20AM");
			Date sdnGetProfileEndDate = new Date();

			String sdnGetProfileCost = ((sdnGetProfileEndDate.getTime() - sdnGetProfileStartDate
					.getTime()) / 1000)
					+ "."
					+ String.format(
							"%1$,03d",
							((sdnGetProfileEndDate.getTime() - sdnGetProfileStartDate
									.getTime()) % 1000));
			errMsg.append(userProfileJson + "\n");

			// System.out.println(authToken);
			Date remotestartEndTime = new Date();

			// System.out
			// .println(" total cost time: "
			// + ((remotestartEndTime.getTime() - startDate
			// .getTime()) / 1000)
			// + "."
			// + ((remotestartEndTime.getTime() - startDate
			// .getTime()) % 1000) + "s.\n");
			String totalCost = ((remotestartEndTime.getTime() - startDate
					.getTime()) / 1000)
					+ "."
					+ String.format("%1$,03d",
							((remotestartEndTime.getTime() - startDate
									.getTime()) % 1000));

			bean.setLightHouseCost(Double.parseDouble(lightHouseCost));
			bean.setSdnLoginUserCost(Double.parseDouble(sdnLoginUserCost));
			// bean.setSdnGetUserProfileCost();
			bean.setInsertDate(new Date());
			bean.setTotalCost(Double.parseDouble(totalCost));
			bean.setSdnGetUserProfileCost(Double.parseDouble(sdnGetProfileCost));
			bean.setOperationStatus(1);
			bean.setStatusDesc(errMsg.toString());
			bean.setRestApiName(Constants.REST_LOGIN_USER_API);
			dao.insertRestAPI(bean);
		} catch (Exception e) {
			bean.setInsertDate(new Date());
			bean.setRestApiName(Constants.REST_LOGIN_USER_API);
			bean.setStatusDesc(e.getMessage() + getStackMsg(e)
					+ errMsg.toString());
			dao.insertRestAPI(bean);
			e.printStackTrace();
		}
	}

	private synchronized void lockAPIs(boolean isDisconnect,
			RestApiParamBean param) {
		RestLoginUserBean bean = new RestLoginUserBean();
		bean.setExeParam(param.toString());
		StringBuffer errMsg = new StringBuffer();
		try {

			RestfulMainHttpsUtil.trustEveryone();
			String loginLHStr = RestfulMainHttpsUtil.loginLightHouse(
					Constants.LIGHT_HOUSE_URL, param.getUnameNotForTestLogin(),
					param.getPasswordNotForTestLogin());
			System.out.println(loginLHStr);
			String lhToken = new JSONObject(loginLHStr)
					.getString("access_token");
			// Step 1 : Login User
			String loginResultJson = RestfulMainHttpsUtil.loginUser(lhToken);
			// System.out.println(loginResultJson);
			String authToken = new JSONObject(loginResultJson)
					.getString("authToken");

			Date startDate = new Date();

			String lockResultJson = RestfulMainHttpsUtil.lock(authToken,
					param.getVin());

			/*
			 * if (isDisconnect) { errMsg.append("TCU sleep mode."+"\n"); } else
			 * { errMsg.append("TCU awake mode."+"\n"); }
			 */

			errMsg.append(lockResultJson + "\n");

			if (!(new JSONObject(lockResultJson).getString("status"))
					.equals("200")) {

				bean.setStatusDesc(errMsg.toString());
				bean.setInsertDate(new Date());
				bean.setRestApiName(Constants.REST_LOCK_API);
				dao.insertRestAPI(bean);
				return;
			}

			String commandId = new JSONObject(lockResultJson)
					.getString("commandId");

			if (!"".equals(commandId)) {
				boolean isNeedStop = true;
				int index = 0;
				while (isNeedStop) {
					// ç¶æcheck > 200ç§
					if ((new Date().getTime() - startDate.getTime()) / 1000 > Constants.REST_API_TIMEOUT) {
						errMsg.append(" time out, over "
								+ Constants.REST_API_TIMEOUT + "s");
						bean.setStatusDesc(errMsg.toString());
						bean.setInsertDate(new Date());
						bean.setRestApiName(Constants.REST_LOCK_API);
						dao.insertRestAPI(bean);
						isNeedStop = false;

						break;
					}

					String lockStatusResultJson = RestfulMainHttpsUtil
							.getLockStatus(authToken, param.getVin(), commandId);
					if (index == 0) {
						errMsg.append(lockStatusResultJson);
					} else {
						errMsg.append(" " + index + " ");
					}
					index = index + 1;
					String status = new JSONObject(lockStatusResultJson)
							.getString("status");
					if ("200".equals(status)) {
						errMsg.append(lockStatusResultJson);
						Date lockEndTime = new Date();
						String value = ((lockEndTime.getTime() - startDate
								.getTime()) / 1000)
								+ "."
								+ String.format("%1$,03d",
										((lockEndTime.getTime() - startDate
												.getTime()) % 1000));

						bean.setTotalCost(Double.parseDouble(value));
						bean.setInsertDate(new Date());
						bean.setStatusDesc(errMsg.toString());
						bean.setOperationStatus(1);
						bean.setRestApiName(Constants.REST_LOCK_API);

						dao.insertRestAPI(bean);
						isNeedStop = false;
						RestfulMainHttpsUtil.unlock(authToken, param.getVin());
					}
				}
			}

		} catch (Exception e) {
			errMsg.append(getStackMsg(e));
			bean.setStatusDesc(errMsg.toString());
			bean.setInsertDate(new Date());
			bean.setRestApiName(Constants.REST_LOCK_API);
			dao.insertRestAPI(bean);
			e.printStackTrace();
		}
	}

	private static String getStackMsg(Throwable e) {

		StringBuffer sb = new StringBuffer();
		StackTraceElement[] stackArray = e.getStackTrace();
		for (int i = 0; i < stackArray.length; i++) {
			StackTraceElement element = stackArray[i];
			sb.append(element.toString() + "\n");
		}
		return sb.toString();
	}

	private synchronized void unlockAPIs(boolean isDisconnect,
			RestApiParamBean param) {
		RestLoginUserBean bean = new RestLoginUserBean();
		bean.setExeParam(param.toString());
		StringBuffer errMsg = new StringBuffer();
		try {

			/*
			 * if (isDisconnect) { errMsg.append("TCU sleep mode." + "\n"); }
			 * else { errMsg.append("TCU awake mode." + "\n"); }
			 */

			RestfulMainHttpsUtil.trustEveryone();
			String loginLHStr = RestfulMainHttpsUtil.loginLightHouse(
					Constants.LIGHT_HOUSE_URL, param.getUnameNotForTestLogin(),
					param.getPasswordNotForTestLogin());
			System.out.println(loginLHStr);
			String lhToken = new JSONObject(loginLHStr)
					.getString("access_token");
			// Step 1 : Login User
			String loginResultJson = RestfulMainHttpsUtil.loginUser(lhToken);
			System.out.println(loginResultJson);
			String authToken = new JSONObject(loginResultJson)
					.getString("authToken");

			Date startDate = new Date();

			String unlockResultJson = RestfulMainHttpsUtil.unlock(authToken,
					param.getVin());
			errMsg.append(unlockResultJson + "\n");
			if (!(new JSONObject(unlockResultJson).getString("status"))
					.equals("200")) {

				bean.setStatusDesc(errMsg.toString());
				bean.setInsertDate(new Date());
				bean.setRestApiName(Constants.REST_UNLOCK_API);

				dao.insertRestAPI(bean);
				return;
			}
			String commandId = new JSONObject(unlockResultJson)
					.getString("commandId");

			if (!"".equals(commandId)) {
				boolean isNeedStop = true;
				int index = 0;
				while (isNeedStop) {
					// ç¶æcheck > 200ç§
					if ((new Date().getTime() - startDate.getTime()) / 1000 > Constants.REST_API_TIMEOUT) {
						errMsg.append("time out, over "
								+ Constants.REST_API_TIMEOUT + "s");
						bean.setStatusDesc(errMsg.toString());
						bean.setInsertDate(new Date());
						bean.setRestApiName(Constants.REST_UNLOCK_API);

						dao.insertRestAPI(bean);
						isNeedStop = false;

						break;
					}
					String unlockStatusResultJson = RestfulMainHttpsUtil
							.getLockStatus(authToken, param.getVin(), commandId);
					// errMsg.append(unlockStatusResultJson+"\n");

					if (index == 0) {
						errMsg.append(unlockStatusResultJson);
					} else {
						errMsg.append(" " + index + " ");
					}
					index = index + 1;

					String status = new JSONObject(unlockStatusResultJson)
							.getString("status");

					if ("200".equals(status)) {
						errMsg.append(unlockStatusResultJson);
						Date unlockEndTime = new Date();
						String value = ((unlockEndTime.getTime() - startDate
								.getTime()) / 1000)
								+ "."
								+ String.format("%1$,03d",
										((unlockEndTime.getTime() - startDate
												.getTime()) % 1000));

						bean.setTotalCost(Double.parseDouble(value));
						bean.setInsertDate(new Date());
						bean.setRestApiName(Constants.REST_UNLOCK_API);
						bean.setStatusDesc(errMsg.toString());
						bean.setOperationStatus(1);
						dao.insertRestAPI(bean);
						isNeedStop = false;
						RestfulMainHttpsUtil.lock(authToken, param.getVin());
					}
				}
			}

		} catch (Exception e) {
			errMsg.append(getStackMsg(e));
			bean.setStatusDesc(errMsg.toString());
			bean.setInsertDate(new Date());
			bean.setRestApiName(Constants.REST_UNLOCK_API);
			dao.insertRestAPI(bean);
			e.printStackTrace();
		}
	}

	private synchronized void remoteStartAlsoCancelStartAPIs(
			boolean isDisconnect, RestApiParamBean param) {
		RestLoginUserBean bean = new RestLoginUserBean();
		bean.setExeParam(param.toString());
		StringBuffer errMsg = new StringBuffer();
		try {

			/*
			 * if (isDisconnect) { errMsg.append("TCU sleep mode." + "\n"); }
			 * else { errMsg.append("TCU awake mode." + "\n"); }
			 */

			RestfulMainHttpsUtil.trustEveryone();
			String loginLHStr = RestfulMainHttpsUtil.loginLightHouse(
					Constants.LIGHT_HOUSE_URL, param.getUnameNotForTestLogin(),
					param.getPasswordNotForTestLogin());
			String lhToken = new JSONObject(loginLHStr)
					.getString("access_token");

			String loginResultJson = RestfulMainHttpsUtil.loginUser(lhToken);

			String authToken = new JSONObject(loginResultJson)
					.getString("authToken");

			Date startDate = new Date();

			String remotestartResultJson = RestfulMainHttpsUtil.start(
					authToken, param.getVin());
			errMsg.append(remotestartResultJson + "\n");
			if (!(new JSONObject(remotestartResultJson).getString("status"))
					.equals("200")) {
				bean.setStatusDesc(errMsg.toString());
				bean.setInsertDate(new Date());
				bean.setRestApiName(Constants.REST_REMOTE_START_API);
				dao.insertRestAPI(bean);
				return;
			}

			String commandId = new JSONObject(remotestartResultJson)
					.getString("commandId");

			if (!"".equals(commandId)) {
				boolean isNeedStop = true;
				int index = 0;
				while (isNeedStop) {
					// ç¶æcheck > 200ç§
					if ((new Date().getTime() - startDate.getTime()) / 1000 > Constants.REST_API_TIMEOUT) {
						errMsg.append("time out, over "
								+ Constants.REST_API_TIMEOUT + "s");
						bean.setStatusDesc(errMsg.toString());
						bean.setInsertDate(new Date());
						bean.setRestApiName(Constants.REST_REMOTE_START_API);
						dao.insertRestAPI(bean);
						isNeedStop = false;

						break;
					}
					String remotestartStatusResultJson = RestfulMainHttpsUtil
							.getStartStatus(authToken, param.getVin(),
									commandId);
					// errMsg.append(remotestartStatusResultJson+"\n");

					if (index == 0) {
						errMsg.append(remotestartStatusResultJson);
					} else {
						errMsg.append(" " + index + " ");
					}
					index = index + 1;
					String status = new JSONObject(remotestartStatusResultJson)
							.getString("status");

					if ("200".equals(status)) {
						errMsg.append(remotestartStatusResultJson);
						Date remotestartEndTime = new Date();

						String value = ((remotestartEndTime.getTime() - startDate
								.getTime()) / 1000)
								+ "."
								+ String.format(
										"%1$,03d",
										((remotestartEndTime.getTime() - startDate
												.getTime()) % 1000));

						bean.setTotalCost(Double.parseDouble(value));
						bean.setInsertDate(new Date());
						bean.setRestApiName(Constants.REST_REMOTE_START_API);
						bean.setStatusDesc(errMsg.toString());
						bean.setOperationStatus(1);
						dao.insertRestAPI(bean);
						isNeedStop = false;

						cancelStartAPIs(param);
					}
				}
			}
		} catch (Exception e) {
			errMsg.append(getStackMsg(e));
			bean.setStatusDesc(errMsg.toString());
			bean.setInsertDate(new Date());
			bean.setRestApiName(Constants.REST_REMOTE_START_API);
			dao.insertRestAPI(bean);
			e.printStackTrace();
		}

	}

	private synchronized void cancelStartAPIs(RestApiParamBean param) {
		RestLoginUserBean bean = new RestLoginUserBean();
		bean.setExeParam(param.toString());
		StringBuffer errMsg = new StringBuffer();
		try {

			RestfulMainHttpsUtil.trustEveryone();
			String loginLHStr = RestfulMainHttpsUtil.loginLightHouse(
					Constants.LIGHT_HOUSE_URL, param.getUnameNotForTestLogin(),
					param.getPasswordNotForTestLogin());
			String lhToken = new JSONObject(loginLHStr)
					.getString("access_token");

			String loginResultJson = RestfulMainHttpsUtil.loginUser(lhToken);

			String authToken = new JSONObject(loginResultJson)
					.getString("authToken");

			Date startDate = new Date();

			String cancelStartResultJson = RestfulMainHttpsUtil.cancelStart(
					authToken, param.getVin());
			errMsg.append(cancelStartResultJson + "\n");
			if (!(new JSONObject(cancelStartResultJson).getString("status"))
					.equals("200")) {
				bean.setStatusDesc(errMsg.toString());
				bean.setInsertDate(new Date());
				bean.setRestApiName(Constants.REST_CANCEL_REMOTE_API);

				dao.insertRestAPI(bean);
				return;
			}

			String commandId = new JSONObject(cancelStartResultJson)
					.getString("commandId");

			if (!"".equals(commandId)) {
				boolean isNeedStop = true;
				int index = 0;
				while (isNeedStop) {
					// ç¶æcheck > 200ç§
					if ((new Date().getTime() - startDate.getTime()) / 1000 > Constants.REST_API_TIMEOUT) {
						errMsg.append("time out, over "
								+ Constants.REST_API_TIMEOUT + "s");
						bean.setStatusDesc(errMsg.toString());
						bean.setInsertDate(new Date());
						bean.setRestApiName(Constants.REST_CANCEL_REMOTE_API);

						dao.insertRestAPI(bean);
						isNeedStop = false;

						break;
					}

					String cancelStartStatusResultJson = RestfulMainHttpsUtil
							.getStartStatus(authToken, param.getVin(),
									commandId);
					// errMsg.append(cancelStartStatusResultJson+"\n");

					if (index == 0) {
						errMsg.append(cancelStartStatusResultJson);
					} else {
						errMsg.append(" " + index + " ");
					}
					index = index + 1;
					String status = new JSONObject(cancelStartStatusResultJson)
							.getString("status");

					if ("200".equals(status)) {
						errMsg.append(cancelStartStatusResultJson);
						Date cancelStartEndTime = new Date();

						String value = ((cancelStartEndTime.getTime() - startDate
								.getTime()) / 1000)
								+ "."
								+ String.format(
										"%1$,03d",
										((cancelStartEndTime.getTime() - startDate
												.getTime()) % 1000));

						bean.setTotalCost(Double.parseDouble(value));
						bean.setInsertDate(new Date());
						bean.setOperationStatus(1);
						bean.setStatusDesc(errMsg.toString());
						bean.setRestApiName(Constants.REST_CANCEL_REMOTE_API);
						dao.insertRestAPI(bean);
						isNeedStop = false;

					}
				}
			}
		} catch (Exception e) {
			errMsg.append(getStackMsg(e));
			bean.setStatusDesc(errMsg.toString());
			bean.setInsertDate(new Date());
			bean.setRestApiName(Constants.REST_CANCEL_REMOTE_API);
			bean.setOperationStatus(0);
			dao.insertRestAPI(bean);
			e.printStackTrace();
		}

	}

	private synchronized void refreshVehicleAPIs(boolean isDisconnect,
			RestApiParamBean param) {
		RestLoginUserBean bean = new RestLoginUserBean();
		bean.setExeParam(param.toString());
		StringBuffer errMsg = new StringBuffer();
		try {

			/*
			 * if (isDisconnect) { errMsg.append("TCU sleep mode." + "\n"); }
			 * else { errMsg.append("TCU awake mode." + "\n"); }
			 */

			RestfulMainHttpsUtil.trustEveryone();
			String loginLHStr = RestfulMainHttpsUtil.loginLightHouse(
					Constants.LIGHT_HOUSE_URL, param.getUnameNotForTestLogin(),
					param.getPasswordNotForTestLogin());
			String lhToken = new JSONObject(loginLHStr)
					.getString("access_token");
			errMsg.append(loginLHStr  + "---");
			// Step 1 : Login User
			String loginResultJson = RestfulMainHttpsUtil.loginUser(lhToken);
			errMsg.append(loginResultJson + "---");
			
			String authToken = new JSONObject(loginResultJson)
					.getString("authToken");
			// Step 2 : refreshStatus
			Date startDate = new Date();
			String refreshStatusResultJson = RestfulMainHttpsUtil
					.refreshVehicleStatus(authToken, param.getVin());
			errMsg.append(refreshStatusResultJson + "---");
			if (!(new JSONObject(refreshStatusResultJson).getString("status"))
					.equals("200")) {
				bean.setStatusDesc(errMsg.toString());
				bean.setInsertDate(new Date());
				bean.setStatusDesc(errMsg.toString());
				bean.setRestApiName(Constants.REST_REFRESH_STATUS_API);

				dao.insertRestAPI(bean);
				return;
			}

			String commandId = new JSONObject(refreshStatusResultJson)
					.getString("commandId");

			// Step 3: get refreshStatus status every 1 second
			if (!"".equals(commandId)) {
				boolean isNeedStop = true;
				int index = 0;
				while (isNeedStop) {
					// ç¶æcheck > 200ç§
					if ((new Date().getTime() - startDate.getTime()) / 1000 > Constants.REST_API_TIMEOUT) {
						errMsg.append("time out, over "
								+ Constants.REST_API_TIMEOUT + "s");
						bean.setStatusDesc(errMsg.toString());
						bean.setInsertDate(new Date());
						bean.setRestApiName(Constants.REST_REFRESH_STATUS_API);

						dao.insertRestAPI(bean);
						isNeedStop = false;

						break;
					}

					String refreshStatusStatusResultJson = RestfulMainHttpsUtil
							.getRefreshVehicleStatus(authToken, param.getVin(),
									commandId);
					// errMsg.append(refreshStatusStatusResultJson+"\n");

					if (index == 0) {
						errMsg.append(refreshStatusStatusResultJson);
					} else {
						errMsg.append(" " + index + " ");
					}
					index = index + 1;

					String status = new JSONObject(
							refreshStatusStatusResultJson).getString("status");

					if ("200".equals(status)) {
						errMsg.append(refreshStatusStatusResultJson);
						Date refreshStatusEndTime = new Date();
						String value = ((refreshStatusEndTime.getTime() - startDate
								.getTime()) / 1000)
								+ "."
								+ ((refreshStatusEndTime.getTime() - startDate
										.getTime()) % 1000);

						bean.setTotalCost(Double.parseDouble(value));
						bean.setInsertDate(new Date());
						bean.setRestApiName(Constants.REST_REFRESH_STATUS_API);
						bean.setOperationStatus(1);
						bean.setStatusDesc(errMsg.toString());
						dao.insertRestAPI(bean);

						isNeedStop = false;
					}
				}
			}

		} catch (Exception e) {
			errMsg.append(getStackMsg(e));
			bean.setStatusDesc(errMsg.toString());
			bean.setInsertDate(new Date());
			bean.setRestApiName(Constants.REST_REFRESH_STATUS_API);
			dao.insertRestAPI(bean);
			e.printStackTrace();
		}
	}

	@Override
	public List<RestLoginUserBean> selectPerformanceByType(
			final String typeName, final String fromDate, final String toDate) {
		final List<RestLoginUserBean> restList = dao.selectPerformanceByType(
				typeName, fromDate, toDate);
		// ExcelExport<RestLoginUserBean> ex = new
		// ExcelExport<RestLoginUserBean>();
		// String[] headers = { "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		// OutputStream out;
		// try {
		// out = new FileOutputStream("c://a.xls");
		// ex.exportExcel(headers, restList, out);
		// } catch (FileNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		new Thread(new Runnable() {

			@Override
			public void run() {
				createExcel(restList, typeName, fromDate, toDate);
			}

		}).start();

		return restList;
	}

	private void createExcel(List<RestLoginUserBean> restList, String typeName,
			String fromDate, String toDate) {
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();

		HSSFSheet sheetDaily = wb.createSheet("Daily Report");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow rowDaily = sheetDaily.createRow(0);
		rowDaily.createCell(0).setCellValue("Date");
		rowDaily.createCell(1).setCellValue("Success Rate");
		rowDaily.createCell(2).setCellValue("Success Count");
		rowDaily.createCell(3).setCellValue("Fail Count");
		rowDaily.createCell(4).setCellValue("Max time(s)");
		rowDaily.createCell(5).setCellValue("Min time(s)");
		rowDaily.createCell(6).setCellValue("Average time(s)");

		try {
			String tempStart = fromDate;
			SimpleDateFormat clock24sdf = new SimpleDateFormat(
					"yyyy/MM/dd HH:mm:ss");
			Calendar toCalender = strDate2calendar(toDate.substring(0, 11)
					+ "00:00:00", clock24sdf);
			toCalender.add(Calendar.DAY_OF_YEAR, 1);

			int i = 0;
			while (true) {
				Calendar tempFromCalender = strDate2calendar(
						tempStart.substring(0, 11) + "00:00:00", clock24sdf);

				String tempFromStr = clock24sdf.format(tempFromCalender
						.getTime());
				Calendar tempToCalender = tempFromCalender;// strDate2calendar(tempStart.substring(0,
															// 11)+"00:00:00",
															// clock24sdf);
				tempToCalender.add(Calendar.DAY_OF_YEAR, 1);
				String tempToStr = clock24sdf.format(tempToCalender.getTime());
				RestLoginUserBean restReportBean = selectFailSuccessPercent(
						typeName, tempFromStr, tempToStr);
				i = i + 1;
				rowDaily = sheetDaily.createRow(i);
				rowDaily.createCell(0)
						.setCellValue(tempStart + "-" + tempToStr);
				rowDaily.createCell(1)
						.setCellValue(
								((double) restReportBean.getSuccessCnt()
										/ (restReportBean.getSuccessCnt() + restReportBean
												.getFailCnt()) * 100)
										+ "%");
				rowDaily.createCell(2).setCellValue(
						restReportBean.getSuccessCnt());
				rowDaily.createCell(3)
						.setCellValue(restReportBean.getFailCnt());
				rowDaily.createCell(4)
						.setCellValue(restReportBean.getMaxTime());
				rowDaily.createCell(5)
						.setCellValue(restReportBean.getMinTime());
				rowDaily.createCell(6).setCellValue(
						restReportBean.getAverageTime());

				System.out.println(tempStart + restReportBean.toString());
				if (clock24sdf.format(toCalender.getTime()).equals(
						clock24sdf.format(tempToCalender.getTime()))) {
					break;
				}
				tempStart = clock24sdf.format(tempFromCalender.getTime());
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}

		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("Data");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);

		if (typeName.equals("LOGIN_USER_API")) {
			row.createCell(0).setCellValue("Light House");
			row.createCell(1).setCellValue("SDN Login User");
			row.createCell(2).setCellValue("SDN Get User Profile");
			row.createCell(3).setCellValue("Total");
			row.createCell(4).setCellValue("API Name");
			row.createCell(5).setCellValue("Result Description");
			row.createCell(6).setCellValue("Insert Date");
			row.createCell(7).setCellValue("Is Success");
		} else {
			row.createCell(0).setCellValue("Total");
			row.createCell(1).setCellValue("API Name");
			row.createCell(2).setCellValue("Result Description");
			row.createCell(3).setCellValue("Insert Date");
			row.createCell(4).setCellValue("Is Success");
		}

		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

		for (int i = 0; i < restList.size(); i++) {
			row = sheet.createRow((int) i + 1);
			RestLoginUserBean stu = restList.get(i);
			// 第四步，创建单元格，并设置值
			if (typeName.equals("LOGIN_USER_API")) {
				row.createCell(0).setCellValue(stu.getLightHouseCost());
				row.createCell(1).setCellValue(stu.getSdnLoginUserCost());
				row.createCell(2).setCellValue(stu.getSdnGetUserProfileCost());
				row.createCell(3).setCellValue(stu.getTotalCost());
				row.createCell(4).setCellValue(stu.getRestApiName());
				row.createCell(5).setCellValue(stu.getStatusDesc());

				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				row.createCell(6).setCellValue(sdf.format(stu.getInsertDate()));
				row.createCell(7).setCellValue(stu.getOperationStatus());
			} else {
				row.createCell(0).setCellValue(stu.getTotalCost());
				row.createCell(1).setCellValue(stu.getRestApiName());
				row.createCell(2).setCellValue(stu.getStatusDesc());

				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				row.createCell(3).setCellValue(sdf.format(stu.getInsertDate()));
				row.createCell(4).setCellValue(stu.getOperationStatus());
			}
		}
		// 第六步，将文件存到指定位置
		try {
			FileOutputStream fout = new FileOutputStream("c:/" + typeName + "_"
					+ fromDate.replaceAll(":", "").replaceAll("/", "") + "_"
					+ toDate.replaceAll(":", "").replaceAll("/", "") + ".xls");
			wb.write(fout);
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Calendar strDate2calendar(String fromDate,
			SimpleDateFormat clock24sdf) throws ParseException {
		Date dtStart = clock24sdf.parse(fromDate);
		Calendar fromCalender = Calendar.getInstance();
		fromCalender.setTime(dtStart);
		return fromCalender;
	}

	@Override
	public RestLoginUserBean selectFailSuccessPercent(String typeName,
			String fromDate, String toDate) {
		return dao.selectSuccessEtcInfo(typeName, fromDate, toDate);
	}
}
