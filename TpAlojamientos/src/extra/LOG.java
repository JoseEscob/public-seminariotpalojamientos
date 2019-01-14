package extra;

import java.util.logging.Logger;

/**
 * @author Jose Permite realizar logs de los sucesos que se deseen informar
 */
public class LOG {
	private static final Logger log = Logger.getLogger(LOG.class.getName());

	public static void info(String msg) {
		log.info(msg);
	}

	public static void warning(String msg) {
		log.warning(msg);
	}
}
