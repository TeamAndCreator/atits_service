package com.atits.controller;

import com.atits.entity.Files;
import com.atits.entity.Msg;
import com.atits.service.FilesService;
import com.atits.service.SystemService;
import com.atits.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@Api(description = "文件")
@RequestMapping(value = "files")
public class FilesController {

    @Resource
    private FilesService filesService;

    @Resource
    private SystemService systemService;

    @Resource
    private UserService userService;


    /**
     * 文件下载
     * 通过id在数据库中查找出一个files对象，
     * 将files.path和files.name拼串，作为文件虚拟路径，转发该虚拟路径，即下载该文件
     * 需在网页上a标签添加*download*属性，否则.txt等文件会被浏览器直接解析。
     * （由于文件保存时名称被改为uuid，原名被保存到files.title中，
     * 所以下载时需在a标签*download*属性中填files.title以重命名）
     * @param id
     * @return
     */
    @RequestMapping(value = "download",method = RequestMethod.GET)
    public String downLoad(Integer id){
        Files files=filesService.findById(id);
        String filePath=files.getPath()+files.getName();
        return "redirect:"+FilesService.getVR_PATH()+filePath;
    }

    @ResponseBody
    @ApiOperation(value = "获取所有文件")
    @RequestMapping(value = "findAll",method = RequestMethod.GET)
    public Msg findAll(){
        try {
            List<Files> files=filesService.findAll();
            List<Map> maps=new ArrayList<>();
            for (Files files1:files){
                String path=files1.getPath();
                String str[]=path.split("/");
                String systemName=systemService.findById(Integer.parseInt(str[0])).getSystemName();
                String userName=userService.findById(Integer.parseInt(str[2])).getProfile().getName();
                Map temp=new HashMap();
                temp.put("files",files1);
                temp.put("systemName",systemName);
                temp.put("userName",userName);
                maps.add(temp);
            }
            return Msg.success().add("files",maps);
        }catch (Exception e){
            return Msg.fail(e.getMessage());
        }
    }
//    @Resource
//    FilesService filesService;
//
//    /**
//     * 保存方法：save方法
//     *
//     * @param files
//     * @return
//     */
//    @RequestMapping(value = "/files_save", method = RequestMethod.POST)
//    public String save(Files files) {
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
//        SimpleDateFormat df1 = new SimpleDateFormat("HH:mm:ss");//设置时间点格式
//        files.setTime(df.format(new Date()));// new Date()为获取当前系统时间
//        files.setDate(df1.format(new Date()));//为获取当前系统时间点
//
//        filesService.save(files);// 封装到service层
//        return "redirect:/files";
//    }
//
//    /**
//     * 文件转变为字符编码
//     *
//     * @param multipartFiles
//     * @return json对象是文件id
//     * @throws IOException
//     */
//
//    @ResponseBody
//    @RequestMapping(value = "/files_upload/{params}", method = RequestMethod.POST)
//    public String upload(@RequestParam("files") MultipartFile[] multipartFiles, @PathVariable("params") String params)
//            throws IOException {
//        ObjectMapper mapper = new ObjectMapper();// json对象建立
//        HashMap map = mapper.readValue(params, HashMap.class);
//        int id = filesService.upload(multipartFiles, map);
//        String json = mapper.writeValueAsString(id);// 将map转换成json字符串
//        return json;
//
//    }
//
//
//    /**
//     * 文件下载
//     *
//     * @param id
//     * @param response
//     * @throws IOException
//     */
//    @RequestMapping(value = "/files_download/{id}", method = RequestMethod.GET)
//    public void download(@PathVariable("id") Integer id, HttpServletResponse response) throws IOException {
//        Files files = filesService.findById(id);
//
//        String FILE_PATH = "C:/file/";
//        String filePath = FILE_PATH + files.getSystem().getId() + "/" + files.getFileType() + "/" + files.getEditor().getId() + "/" + files.getFileName();
//        File file = new File(filePath);
//        if (file.exists()) {
//            String filename = URLEncoder.encode(files.getFileName(), "UTF-8");
//            //response.setContentType("application/pdf");
//            response.setHeader("Content-disposition", "attachment;filename=" + filename);
//            byte[] buffer = new byte[1024];
//            FileInputStream fis = null;
//            BufferedInputStream bis = null;
//            fis = new FileInputStream(file);
//            bis = new BufferedInputStream(fis);
//            OutputStream os = response.getOutputStream();
//            int i = bis.read(buffer);
//            while (i != -1) {
//                os.write(buffer, 0, i);
//                i = bis.read(buffer);
//            }
//            os.flush();
//            os.close();
//            bis.close();
//            fis.close();
//
//        } else {
//            response.setContentType("text/html");
//            response.setCharacterEncoding("UTF-8");
//            PrintWriter pwt = response.getWriter();
//            pwt.println("文件不存在");
//            pwt.close();
//        }
//
//    }

}
