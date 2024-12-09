package com.example.ordertaker.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Tag(name = "图片上传接口", description = "/api/image")
@RestController
@RequestMapping("/api/image")
public class UploadController {

    private static final String IMAGE_CLIENT_FOLDER = "image/client/";

    private static final String IMAGE_COURIER_FOLDER = "image/courier/";

    /**
     * 上传用户头像
     *
     * @param file     头像图片
     * @param clientId 用户ID
     * @return 是否成功
     */
    @Parameters({
            @Parameter(name = "file", description = "头像图片", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "clientId", description = "用户ID", in = ParameterIn.QUERY, required = true)
    })
    @Operation(summary = "上传用户头像", description = "上传用户头像")
    @PostMapping("/client")
    public String clientSingleImageUpload(@RequestParam("file") MultipartFile file,
                                    @RequestParam("clientId") Long clientId) {
        return singleImageUpload(file, clientId, IMAGE_CLIENT_FOLDER);
    }

    /**
     * 上传骑手头像
     *
     * @param file      头像图片
     * @param courierId 骑手ID
     * @return 是否成功
     */
    @Parameters({
            @Parameter(name = "file", description = "头像图片", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "courierId", description = "骑手ID", in = ParameterIn.QUERY, required = true)
    })
    @Operation(summary = "上传骑手头像", description = "上传骑手头像")
    @PostMapping("/courier")
    public String courierSingleImageUpload(@RequestParam("file") MultipartFile file,
                                           @RequestParam("courierId") Long courierId) {
        return singleImageUpload(file, courierId, IMAGE_COURIER_FOLDER);
    }


    /**
     * 上传图片
     *
     * @param file   图片
     * @param id     用户ID
     * @param folder 文件夹
     * @return 是否成功
     */
    public String singleImageUpload(MultipartFile file, Long id, String folder) {
        try {
            // Save the uploaded file to the specified directory
            byte[] bytes = file.getBytes();
            String fileExtension = file.getOriginalFilename().split("\\.")[1];
            String filename = folder + id + "." + fileExtension;
            File uploadedFile = new File(filename);
            uploadedFile.createNewFile();
            java.nio.file.Files.write(uploadedFile.toPath(), bytes);
            return filename;
        } catch (IOException e) {
            e.printStackTrace();
            return "Error while uploading file";
        }
    }


    /**
     * 获取用户头像
     *
     * @param filename 用户头像文件名
     * @return 用户头像
     */
    @Parameter(name = "filename", description = "用户头像文件名", in = ParameterIn.QUERY, required = true)
    @Operation(summary = "获取用户头像", description = "获取用户头像")
    @GetMapping("/getClientImage/{filename:.+}")
    public ResponseEntity<byte[]> getClientImage(@PathVariable String filename) {
        return getFileUtil(filename, IMAGE_CLIENT_FOLDER);
    }


    /**
     * 获取骑手头像
     *
     * @param filename 骑手头像文件名
     * @return 骑手头像
     */
    @Parameter(name = "filename", description = "骑手头像文件名", in = ParameterIn.QUERY, required = true)
    @Operation(summary = "获取骑手头像", description = "获取骑手头像")
    @GetMapping("/getCourierImage/{filename:.+}")
    public ResponseEntity<byte[]> getCourierImage(@PathVariable String filename) {
        return getFileUtil(filename, IMAGE_COURIER_FOLDER);
    }

    /**
     * 获取图片
     *
     * @param filename 图片文件名
     * @param studentPdf 图片文件夹
     * @return 图片
     */
    private ResponseEntity<byte[]> getFileUtil(@PathVariable String filename, String studentPdf) {
        try {
            // Retrieve the file from the specified directory
            Path path = Paths.get(studentPdf + filename);
            byte[] pdf = Files.readAllBytes(path);

            // Return the video as the response body
            return new ResponseEntity<>(pdf, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
