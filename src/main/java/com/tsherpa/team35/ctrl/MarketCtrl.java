package com.tsherpa.team35.ctrl;

import com.tsherpa.team35.biz.MarketService;
import com.tsherpa.team35.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("market")
public class MarketCtrl {
    @Autowired
    MarketService marketService;
    @Value("${spring.servlet.multipart.location}")
    String uploadFolder;

    private final ResourceLoader resourceLoader;

    public MarketCtrl(ResourceLoader resourceLoader){
        this.resourceLoader = resourceLoader;
    }

    @GetMapping("/marketList")
    public String list(Model model)throws Exception{

        List<MainVO> mainList = marketService.mainVOList();
        model.addAttribute("mainList",mainList);
        System.out.println("============================");
        System.out.println(mainList.toString());
        System.out.println("============================");
        return "market/marketList";
    }

    @PostMapping("/reqInsert")
    public String reqInsert(Model model)throws Exception{

        return "redirect:marketList";
    }

    @GetMapping("/reqInsert")
    public String marketInsert(Model model)throws Exception{

        return "market/reqInsert";
    }

    @GetMapping("/marketInsert")
    public String insertMarket(Model model)throws Exception{

        return "market/marketInsert";
    }

    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public String write(Market market, @RequestParam("upfile") MultipartFile[] repImage,@RequestParam("detailFile") MultipartFile[] detailImages ,HttpServletRequest req, Model model, RedirectAttributes rttr, Principal principal) throws Exception {
        String sid = principal != null ? principal.getName() : "";

        String realPath = "C://upload";
        System.out.println(realPath);           // 업로드 경로 설정
//        String realPath = "/Users/juncheol/Desktop/fileupload";    // 업로드 경로 설정

        String today = new SimpleDateFormat("yyMMdd").format(new Date());
        String repImageSaveFolder = "rep_images/" + today;
        String detailImageSaveFolder = "detail_images/" + today;

        File repImageFolder = new File(repImageSaveFolder);
        File detailImageFolder = new File(detailImageSaveFolder);


        if (!repImageFolder.exists()) {
            repImageFolder.mkdirs();
        }

        if (!detailImageFolder.exists()) {
            detailImageFolder.mkdirs();
        }
        List<Mainphoto> mainphotoList = new ArrayList<>();
        for (MultipartFile mainphoto : repImage) {
            Mainphoto main = new Mainphoto();
            String originalmainName = mainphoto.getOriginalFilename();
            if (!originalmainName.isEmpty()) {
                String savemainName = UUID.randomUUID().toString() + originalmainName.substring(originalmainName.lastIndexOf("."));
                main.setSaveFile(today);
                main.setSaveFile(savemainName);
                main.setOriginFile(originalmainName);
                main.setSaveFolder(repImageSaveFolder);
                mainphoto.transferTo(new File(repImageSaveFolder, savemainName));
            }
            mainphotoList.add(main);
        }
        market.setMainphotoList(mainphotoList);


        List<Photos> fileInfoList = new ArrayList<>();        // 첨부파일 정보를 리스트로 생성
        for(MultipartFile file : detailImages) {
            Photos fileInfo = new Photos();
            String originalFileName = file.getOriginalFilename(); // 첨부파일의 실제 파일명
            System.out.println("파일명 : "+originalFileName);
            if(!originalFileName.isEmpty()) {
                String saveFileName = UUID.randomUUID().toString() + originalFileName.substring(originalFileName.lastIndexOf("."));     // 파일 이름을 랜덤으로 설정
                fileInfo.setSaveFile(today);
                fileInfo.setOriginFile(originalFileName);
                fileInfo.setSaveFile(saveFileName);
                fileInfo.setSaveFolder(detailImageSaveFolder);
                file.transferTo(new File(detailImageFolder, saveFileName));    // 파일을 업로드 폴더에 저장
            }
            fileInfoList.add(fileInfo);
        }
        market.setFileInfoList(fileInfoList);
        market.setLoginId(sid);

        try {
            marketService.marketInsert(market);
            rttr.addFlashAttribute("msg", "자료실에 글을 등록하였습니다");
        } catch(Exception e) {
            e.printStackTrace();
            rttr.addFlashAttribute("msg", "글 작성 중 문제가 발생했습니다");
        }

        return "redirect:/market/marketList";
    }

    @GetMapping("/detail")

    public String marketDetail(@RequestParam("marketNo") int marketNo, Model model)throws Exception{
        System.out.println("마켓엔오~~!!!:"+marketNo);
        MainVO market = marketService.mainlistForDetailVOList(marketNo);
        System.out.println("market: "+market);

        model.addAttribute("market",market);
        return "market/marketDetail";
    }

    @GetMapping("/mainImage")
    public ResponseEntity<Resource> download1(@ModelAttribute MainVO dto) throws IOException {
        Path path = Paths.get(uploadFolder + "/" + dto.getSaveFolder()+"/"+dto.getSaveFile());
        System.out.println(path);
        String contentType = Files.probeContentType(path);
        // header를 통해서 다운로드 되는 파일의 정보를 설정한다.
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(ContentDisposition.builder("attachment")
                .filename(dto.getOriginFile(), StandardCharsets.UTF_8)
                .build());
        headers.add(HttpHeaders.CONTENT_TYPE, contentType);

        Resource resource = new InputStreamResource(Files.newInputStream(path));
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

    @GetMapping("/totalImage")
    public ResponseEntity<Resource> download3(@ModelAttribute TotalVO dto) throws IOException {
        Path path = Paths.get(uploadFolder + "/" + dto.getMainSaveFolder()+"/"+dto.getMainSaveFile());
        System.out.println(path);
        String contentType = Files.probeContentType(path);
        // header를 통해서 다운로드 되는 파일의 정보를 설정한다.
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(ContentDisposition.builder("attachment")
                .filename(dto.getMainOriginFile(), StandardCharsets.UTF_8)
                .build());
        headers.add(HttpHeaders.CONTENT_TYPE, contentType);

        Resource resource = new InputStreamResource(Files.newInputStream(path));
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

    @GetMapping("/detailImage")
    public ResponseEntity<Resource> download2(@ModelAttribute DetailVO dto) throws IOException {
        Path path = Paths.get(uploadFolder + "/" + dto.getSaveFolder()+"/"+dto.getSaveFile());
        System.out.println(path);
        String contentType = Files.probeContentType(path);
        // header를 통해서 다운로드 되는 파일의 정보를 설정한다.
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(ContentDisposition.builder("attachment")
                .filename(dto.getOriginFile(), StandardCharsets.UTF_8)
                .build());
        headers.add(HttpHeaders.CONTENT_TYPE, contentType);

        Resource resource = new InputStreamResource(Files.newInputStream(path));
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

    @GetMapping("/delete")
    public String marketDelete(@RequestParam int marketNo)throws Exception{
        marketService.marketDelete(marketNo);
        return "redirect:/market/marketList";
    }


}
