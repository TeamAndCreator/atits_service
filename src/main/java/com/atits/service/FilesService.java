package com.atits.service;

import com.atits.dao.FilesDao;
import com.atits.entity.Files;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;


/**
 * @author YXX
 * @Date 2017年6月20日
 * @类型 filesService
 */
/* @Service业务层，事务型：@Transactional */
@Transactional
@Service
public class FilesService {

    @Resource
    private FilesDao filesDao;// 注入dao数据

    private final String REAL_PATH="/root/File";

    private final static String VR_PATH="/File/";

    public static String getVR_PATH() {
        return VR_PATH;
    }

    public Files findById(Integer id){
        return filesDao.findById(id);
    }



    /* files:是实体类 */
//    public int upload(MultipartFile[] multipartFiles) throws IOException {
//        byte[] data = null;
//        BASE64Encoder encoder = new BASE64Encoder();
//        String title = null;
//        String fileName = null;
//        for (MultipartFile multipartFile : multipartFiles) {
//            if (multipartFile.isEmpty()) {
//                System.out.println("文件未上传");
//            } else {
//                InputStream in = multipartFile.getInputStream();
//                data = new byte[in.available()];
//                in.read(data);
//                in.close();
//                title = multipartFile.getOriginalFilename();// 获得文件名
//
//                //检查文件后缀格式
//                String fileEnd = title.substring(title.lastIndexOf(".") + 1).toLowerCase();
//                //创建文件唯一名称
//                String uuid = UUID.randomUUID().toString();
//                fileName = uuid + "." + fileEnd;
//                System.out.println(fileName);
//            }
//        }
//        Files files = new Files();
//        files.setTitle(title);
//        files.setFileName(fileName);
//        files.setContent(encoder.encode(data));
//        return filesDao.getIdOfSave(files);
//
//    }


//    /* files:是实体类 */
//    public int upload(MultipartFile[] multipartFiles, HashMap map) throws IOException {
//        String title = null;
//        String fileName = null;
//        for (MultipartFile multipartFile : multipartFiles) {
//            if (multipartFile.isEmpty()) {
//                System.out.println("文件未上传");
//            } else {
//
//                title = multipartFile.getOriginalFilename();// 获得文件名
//
//                //检查文件后缀格式
//                String fileEnd = title.substring(title.lastIndexOf(".") + 1).toLowerCase();
//                //创建文件唯一名称
//                String uuid = UUID.randomUUID().toString();
//                fileName = uuid + "." + fileEnd;
//                String filePath = "C:/file/" + map.get("sysId") + "/" + map.get("type") + "/" + map.get("personId") + "/" + fileName;
//                File file = new File(filePath);
//
//                //判断目标文件所在的目录是否存在
//                if (!file.getParentFile().exists()) {
//                    //如果目标文件所在的目录不存在，则创建父目录
//                    System.out.println("目标文件所在目录不存在，准备创建它！");
//                    if (!file.getParentFile().mkdirs()) {
//                        System.out.println("创建目标文件所在目录失败！");
//                    }
//                }
//                if (file.createNewFile()) {
//                    System.out.println("创建单个文件" + fileName + "成功！");
//                } else {
//                    System.out.println("创建单个文件" + fileName + "失败！");
//                }
//                multipartFile.transferTo(file);
//            }
//        }
//        Files files = new Files();
//        files.setTitle(title);
//        files.setFileName(fileName);
//        return filesDao.getIdOfSave(files);
//
//    }

    /**
     * 保存一个文件（file）到服务器指定处（例如到File/Activity/system.id/user.id/fileName路径下等）
     * 并返回一个实体类（Files）用于在上一层（例如ActivityController等）将file保存到数据库
     */
    public Set<Files> fileSave(MultipartFile[] multipartFiles, String fileType, int systemId, int userId, String date, String time) throws IOException {
        //物理路径
        String path=systemId+"/"+fileType+"/"+userId+"/";
        String realPath = REAL_PATH + path;
        Set<Files> filesSet=new HashSet<Files>();
        for (MultipartFile multipartFile:multipartFiles){
            String title = multipartFile.getOriginalFilename();
            //获取文件后缀
            String fileEnd = title.substring(title.lastIndexOf(".") + 1).toLowerCase();
            //创建唯一文件名
            String uuid= UUID.randomUUID().toString();
            String fileName=uuid+"."+fileEnd;
            File file = new File(realPath, fileName);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            multipartFile.transferTo(new File(realPath + File.separator + fileName));
            //数据库中要保存虚拟路径，才可用于下载
            filesSet.add(new Files(fileName,fileType,title,path,time,date));
        }
        return filesSet;
    }

    /**
     * 删除一组文件(file)，不删除数据库记录，删除新闻时级联删除，用于删除新闻
     */
    public void deleteFiles(Set<Files> filesSet){
        for (Files files:filesSet){
            String path=REAL_PATH + files.getPath();
            String fileName=files.getName();
            File file=new File(path,fileName);
            if (file.exists()){
                file.delete();
            }
        }
    }
    /**
     * 删除一组文件(file),并删除数据库中记录，用于新闻更新
     */
    public void deleteDoubleFiles(Set<Files> filesSet){
        for (Files files:filesSet){
            filesDao.deleteById(files.getId());
            String path=REAL_PATH + files.getPath();
            String fileName=files.getName();
            File file=new File(path,fileName);
            if (file.exists()){
                file.delete();
            }
        }
    }
//
//
//    /* 批量删除： */
//    public void deletes(List<Integer> idList) {
//        filesDao.deletes(idList);
//    }
//
//
//    public void updateTimeAndEditor(Integer id, String time, String date, Integer editor_id, Integer sysId, String fileType) {
//        filesDao.updateTimeAndEditor(id, time, date, editor_id, sysId, fileType);
//    }
//
//    /* 更新方法：update */
//    public void update(Files files) {
//        filesDao.save(files);
//    }
//
//    /* 更新状态：files state update */
//    public void updateState(Integer id, Integer val) {
//        filesDao.updateState(id, val);
//
//    }
//
//    public List<Files> findAllBySysId(Integer sysId) {
//        // TODO Auto-generated method stub
//        return filesDao.findAllBySysId(sysId);
//    }

    public List findAll(){
        return filesDao.findAll();
    }
}
