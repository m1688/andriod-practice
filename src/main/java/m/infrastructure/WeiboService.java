/**
 * 
 */
package m.infrastructure;

import java.util.List;

import m.domain.user.Weibo;

/**
 * @author wei.xinw
 *
 */
public interface WeiboService {
	/**
	 * ����΢��
	 * @param weibo
	 * @return
	 */
	boolean send(Weibo weibo);
	
	/**
	 * ��ȡ�û�΢���б�
	 * @param userId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<Weibo> getList(Long userId,int pageNo,int pageSize);
	
	/**
	 * ��ȡ�û���Ҫ΢��
	 * @param userId
	 * @return
	 */
	List<Weibo> getAll(Long userId);

}
