package com.nguyentuantai_2180601350.Baiktra.repository;



import com.nguyentuantai_2180601350.Baiktra.model.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, String> {

}
