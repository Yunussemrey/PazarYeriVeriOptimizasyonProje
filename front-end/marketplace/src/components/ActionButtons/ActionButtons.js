import React from "react";
import "./ActionButtons.css";

function ActionButtons() {
  return (
    <div className="action-buttons">
      <button className="btn btn-primary" title="Görüntüle">
        Görüntüle
      </button>
      <button className="btn btn-success" title="Onayla">
        Onayla
      </button>
      <button className="btn btn-danger" title="Reddet">
        Reddet
      </button>
      <button className="btn btn-secondary" title="Loglar">
        Loglar
      </button>
      <button className="btn btn-info" title="PDF">
        PDF
      </button>
      <button className="btn btn-warning" title="Excel">
        Excel
      </button>
      <button className="btn btn-dark" title="Rapor">
        Rapor
      </button>
    </div>
  );
}

export default ActionButtons;
