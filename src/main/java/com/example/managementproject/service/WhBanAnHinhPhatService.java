package com.example.managementproject.service;

import com.example.managementproject.dto.WhBanAnHinhPhatRequest;
import com.example.managementproject.dto.WhBanAnHinhPhatResponse;
import com.example.managementproject.entity.WhBanAnHinhPhat;
import com.example.managementproject.entity.WhBanAnLenhQd;
import com.example.managementproject.repository.WhBanAnHinhPhatRepository;
import com.example.managementproject.repository.WhBanAnLenhQdRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WhBanAnHinhPhatService {
    @Autowired
    WhBanAnLenhQdRepository whBanAnLenhQdRepository;

    @Autowired
    WhBanAnHinhPhatRepository whBanAnHinhPhatRepository;

    @Autowired
    ModelMapper modelMapper;

    @Transactional
    public WhBanAnHinhPhatResponse create(WhBanAnHinhPhatRequest request) {
        WhBanAnLenhQd banAn = whBanAnLenhQdRepository.findById(request.getBanAnLenhQdId())
                .orElseThrow(() -> new RuntimeException("Khong tim thay ban an hinh phat"));

        WhBanAnHinhPhat wd = modelMapper.map(request, WhBanAnHinhPhat.class);

        wd.setBanAnLenhQd(banAn);
        wd.setThaoTacCuoi(1);
        wd.setSyncVnpt(0);
        wd.setTimeSyncVnpt(new Date());

        WhBanAnHinhPhat saved = whBanAnHinhPhatRepository.save(wd);
        return convertToResponse(saved);
    }

    @Transactional
    public WhBanAnHinhPhatResponse update(Long id, WhBanAnHinhPhatRequest request){
        WhBanAnHinhPhat banAn = whBanAnHinhPhatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Khong tim thay ban an"));

        modelMapper.map(request,banAn);
        if(request.getBanAnLenhQdId() != null){
            WhBanAnLenhQd banAnQd = whBanAnLenhQdRepository.findById(request.getBanAnLenhQdId())
                    .orElseThrow(() -> new RuntimeException("Khong tim thay ban an"));
            banAn.setBanAnLenhQd(banAnQd);
        }

        banAn.setNgaySuaCuoi(new Date());
        banAn.setThaoTacCuoi(2);
        banAn.setSyncVnpt(0);
        banAn.setTimeSyncVnpt(new Date());

        return convertToResponse(whBanAnHinhPhatRepository.save(banAn));
    }

    public WhBanAnHinhPhatResponse convertToResponse(WhBanAnHinhPhat entity){
        WhBanAnHinhPhatResponse res = modelMapper.map(entity, WhBanAnHinhPhatResponse.class);
        if(entity.getBanAnLenhQd() != null){
            res.setBanAnLenhQdId(entity.getBanAnLenhQd().getId());
        }
        return res;
    }

    @Transactional
    public void delete(Long id){
        if(!whBanAnHinhPhatRepository.existsById(id)){
            throw new RuntimeException("Khong tim thay ban an ");
        }
        whBanAnHinhPhatRepository.deleteById(id);
    }
}
