package org.wid.jless.log;

import org.apache.commons.logging.Log;
import org.wid.jless.json.JLessJson;


class LogBean implements Log{
	private Log log = null;
	
	protected LogBean(Log log) {
		this.log = log;
	}
	@Override
	public void debug(Object arg0) {
		if (JLessLog.logLevel <= JLessLog.DEBUG) {
			log.error(JLessLog.LOG_PREFIX+JLessJson.stringify(arg0));
		}		
	}

	@Override
	public void debug(Object arg0, Throwable arg1) {
		if (JLessLog.logLevel <= JLessLog.DEBUG) {
			log.error(JLessLog.LOG_PREFIX+JLessJson.stringify(arg0),arg1);
		}
	}

	@Override
	public void error(Object arg0) {
		if (JLessLog.logLevel <= JLessLog.ERROR) {
			log.error(JLessLog.LOG_PREFIX+JLessJson.stringify(arg0));
		}		
	}

	@Override
	public void error(Object arg0, Throwable arg1) {
		if (JLessLog.logLevel <= JLessLog.ERROR) {
			log.error(JLessLog.LOG_PREFIX+JLessJson.stringify(arg0),arg1);
		}		
	}

	@Override
	public void fatal(Object arg0) {
		log.fatal(arg0);
	}

	@Override
	public void fatal(Object arg0, Throwable arg1) {
		log.fatal(arg0,arg1);
	}

	@Override
	public void info(Object arg0) {
		if (JLessLog.logLevel <= JLessLog.INFO) {
			log.error(JLessLog.LOG_PREFIX+JLessJson.stringify(arg0));
		}		
	}

	@Override
	public void info(Object arg0, Throwable arg1) {
		if (JLessLog.logLevel <= JLessLog.INFO) {
			log.error(JLessLog.LOG_PREFIX+JLessJson.stringify(arg0),arg1);
		}				
	}

	@Override
	public boolean isDebugEnabled() {
		return log.isDebugEnabled();
	}

	@Override
	public boolean isErrorEnabled() {
		return log.isErrorEnabled();
	}

	@Override
	public boolean isFatalEnabled() {
		return log.isFatalEnabled();
	}

	@Override
	public boolean isInfoEnabled() {
		return log.isInfoEnabled();
	}

	@Override
	public boolean isTraceEnabled() {
		return log.isTraceEnabled();
	}

	@Override
	public boolean isWarnEnabled() {
		return log.isWarnEnabled();
	}

	@Override
	public void trace(Object arg0) {
		log.trace(arg0);
	}

	@Override
	public void trace(Object arg0, Throwable arg1) {
		log.trace(arg0,arg1);
	}

	@Override
	public void warn(Object arg0) {
		if (JLessLog.logLevel <= JLessLog.WARN) {
			log.error(JLessLog.LOG_PREFIX+JLessJson.stringify(arg0));
		}				
	}

	@Override
	public void warn(Object arg0, Throwable arg1) {
		if (JLessLog.logLevel <= JLessLog.WARN) {
			log.error(JLessLog.LOG_PREFIX+JLessJson.stringify(arg0),arg1);
		}				
	}

}
