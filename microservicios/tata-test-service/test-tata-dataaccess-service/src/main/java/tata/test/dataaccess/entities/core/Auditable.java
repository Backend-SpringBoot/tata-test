package tata.test.dataaccess.entities.core;

public interface Auditable<P> extends Dated {

  P getIdPersonaCrea();

  void setIdPersonaCrea(P idPersonaCrea);

  P getIdPersonaModifica();

  void setIdPersonaModifica(P idPersonaModifica);

  String getIpCrea();

  void setIpCrea(String ipCrea);

  String getIpModifica();

  void setIpModifica(String ipModifica);

  String getEquipoModifica();

  void setEquipoModifica(String equipoModifica);

  String getEquipoCrea();

  void setEquipoCrea(String equipoCrea);

  String getMotivoModifica();

  void setMotivoModifica(String motivoModifica);
}
