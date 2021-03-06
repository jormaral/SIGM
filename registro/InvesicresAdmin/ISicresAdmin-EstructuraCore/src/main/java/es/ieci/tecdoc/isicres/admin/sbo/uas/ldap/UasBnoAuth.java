package es.ieci.tecdoc.isicres.admin.sbo.uas.ldap;

import es.ieci.tecdoc.isicres.admin.base.dbex.DbConnection;
import es.ieci.tecdoc.isicres.admin.core.db.DBSessionManager;
import es.ieci.tecdoc.isicres.admin.core.ldap.LdapConnCfg;
import es.ieci.tecdoc.isicres.admin.sbo.config.CfgMdoConfig;
import es.ieci.tecdoc.isicres.admin.sbo.uas.base.UasAuthToken;



public final class UasBnoAuth
{

	//~ Constructors -----------------------------------------------------------

	private UasBnoAuth(){}
	
	//~ Methods ----------------------------------------------------------------

	/**
	 * @autor IECISA
	 * 
	 * @param name				 Login del usuario
	 * @param pwd				 Password introducida por el usuario al logarse
	 * @param cntsTriesNum	 N�mero de intentos de login que ha realizado el usuario
	 * 
	 * @return UasAuthToken	Informaci�n completa del usuario
	 * @throws Exception		Exception if the application business logic throws an exception
	 * 
	 * @since V1.0
	 */
	public static UasAuthToken authenticateUser(String name, String pwd,
												           int cntsTriesNum, String entidad)
									   throws Exception
	{

		UasAuthToken   token    = null;		
		LdapConnCfg    ldapCfg  = null;
		UasAuthConfig  authCfg  = null;

		DbConnection dbConn=new DbConnection();
		try{
			dbConn.open(DBSessionManager.getSession());
			
			ldapCfg = UasConfigUtil.createLdapConnConfig(entidad);
			authCfg = UasConfigUtil.createUasAuthConfig(entidad);			
			
			token = UasMdoAuth.authenticateUser(dbConn, ldapCfg, authCfg, name, 
			                                    pwd, cntsTriesNum, entidad);
			return token;

		}
		 catch(Exception e)
		{

			return token;
		}finally{
			dbConn.close();
		}

	}	
	
	public static UasAuthToken authenticateUser(String name, String entidad)
										 throws Exception
	{

		UasAuthToken   token    = null;		
		LdapConnCfg    ldapCfg  = null;
		UasAuthConfig  authCfg  = null;

		DbConnection dbConn=new DbConnection();
		try{
			dbConn.open(DBSessionManager.getSession());
			
			ldapCfg = UasConfigUtil.createLdapConnConfig(entidad);
			authCfg = UasConfigUtil.createUasAuthConfig(entidad);	
			
			token = UasMdoAuth.authenticateUser(dbConn, ldapCfg, authCfg, name, entidad);

			return token;

		}
		 catch(Exception e)
		{

			return token;

		}finally{
			dbConn.close();
		}

	}
	
}
 // class
