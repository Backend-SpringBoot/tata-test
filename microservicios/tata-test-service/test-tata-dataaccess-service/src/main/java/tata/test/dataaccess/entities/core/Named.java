package tata.test.dataaccess.entities.core;

import java.io.Serializable;

public interface Named extends Serializable {

  String getNombre();

  void setNombre(String nombre);

  String getDescripcion();

  void setDescripcion(String descripcion);

}
