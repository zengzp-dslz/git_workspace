package cn.ootv.module;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class HessianCodecFactory
{
  public byte[] serialize(Object object)
  {
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    HessianOutput ho = new HessianOutput(os);
    try {
      ho.writeObject(object);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return os.toByteArray();
  }

  public Object deserialize(byte[] data) {
    ByteArrayInputStream is = new ByteArrayInputStream(data);
    return deserialize(is);
  }

  public Object deserialize(InputStream is) {
    HessianInput hi = new HessianInput(is);
    try {
      return hi.readObject();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}