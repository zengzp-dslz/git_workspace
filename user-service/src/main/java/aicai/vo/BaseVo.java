package aicai.vo;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import net.sf.cglib.beans.BeanCopier;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.google.common.collect.Lists;

public class BaseVo implements Serializable {
	private static final transient long serialVersionUID = 1L;
	private static ConcurrentMap<String, BeanCopier> beanCopiers = new ConcurrentHashMap<String, BeanCopier>();

	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE).toString();
	}

	public String toLineString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE).toString();
	}

	@SuppressWarnings("unchecked")
	public <T> T copyTo(Class<T> clz) {
		try {
			Object clone = clz.newInstance();
			String key = clz.getName() + ":" + getClass().getName();

			BeanCopier copier = null;
			if (beanCopiers.containsKey(key)) {
				copier = (BeanCopier) beanCopiers.get(key);
			} else {
				copier = BeanCopier.create(getClass(), clz, false);
				beanCopiers.putIfAbsent(key, copier);
			}
			copier.copy(this, clone, null);
			return (T) clone;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <A extends BaseVo, B extends BaseVo> List<B> copyListTo(
			List<A> sources, Class<B> clz) {
		try {
			if (sources == null) {
				return null;
			}
			List newList = Lists.newArrayList();
			if (sources.size() == 0) {
				return newList;
			}
			BaseVo val = (BaseVo) sources.get(0);
			String key = clz.getName() + ":" + val.getClass().getName();

			BeanCopier copier = null;
			if (beanCopiers.containsKey(key)) {
				copier = (BeanCopier) beanCopiers.get(key);
			} else {
				copier = BeanCopier.create(val.getClass(), clz, false);
				beanCopiers.putIfAbsent(key, copier);
			}
			for (BaseVo a : sources) {
				BaseVo clone = (BaseVo) clz.newInstance();
				copier.copy(a, clone, null);
				newList.add(clone);
			}
			return newList;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}