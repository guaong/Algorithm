package other;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 根据实际需求
 * 需要对不同信息的课程表按照时分秒进行排序
 * 为了满足通用性
 * 将不同课程表对象抽象一个父类ObjectScheduleVO
 * 子类只需要继承ObjectScheduleVO就可以调用getListByDescHour按照时分秒进行排序
 */
@Data
public class ObjectScheduleVO {

    @DateTimeFormat(pattern = "HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy.MM.dd")
    private Date startmonth;

    @DateTimeFormat(pattern = "HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "HH:mm:ss")
    private Date starthour;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy.MM.dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date starttime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy.MM.dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endtime;

    public static void main(String[] args) {
        List<ObjectScheduleVO> list = new ArrayList<>();
        ObjectScheduleVO.getListByDescHour(ObjectScheduleVO.clipDate(list));
    }

    /**
     * list按照时分秒从低到高排列
     * @param list List<ClassroomScheduleVO>
     * @return List<ClassroomScheduleVO>
     */
    public static List<ObjectScheduleVO> getListByDescHour(List<? extends ObjectScheduleVO> list){
        // 升序队列
        List<ObjectScheduleVO> descList = new ArrayList<>();
        for (ObjectScheduleVO vo : list){
            Calendar c = Calendar.getInstance();
            c.setTime(vo.getStarttime());
            // 从原始队列中取出的ClassroomScheduleVO,换算时间(只计算时分秒的总毫秒数)
            int cl = c.get(Calendar.HOUR_OF_DAY) * 3600 + c.get(Calendar.MINUTE) * 60 + c.get(Calendar.SECOND);
            int i = 0;
            // 将原始队列中的ClassroomScheduleVO和升序队列中的每一个比较
            for (; i < descList.size(); i++){
                Calendar descc = Calendar.getInstance();
                descc.setTime(descList.get(i).getStarthour());
                int desccl = descc.get(Calendar.HOUR_OF_DAY) * 3600 + descc.get(Calendar.MINUTE) * 60 + descc.get(Calendar.SECOND);
                // 将原始队列中的ClassroomScheduleVO和升序队列中的每一个比较，小于等于则直接前插
                if (cl < desccl){
//                    descList.add(i,vo);
                    break;
                }
            }
            descList.add(i, vo);
        }
        return descList;
    }

    /**
     * 将日期拆分成年月日和时分秒，再赋值
     * @param list
     * @return
     */
    public static List<? extends ObjectScheduleVO> clipDate(List<? extends ObjectScheduleVO> list){
        for (ObjectScheduleVO c : list
        ) {
            if (c.getStarttime() != null) {
                c.setStarthour(c.getStarttime());
                c.setStartmonth(c.getStarttime());
            }
        }
        return list;
    }
}
